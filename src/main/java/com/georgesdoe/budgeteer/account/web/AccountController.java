package com.georgesdoe.budgeteer.account.web;

import com.georgesdoe.budgeteer.account.domain.AccountService;
import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Accounts", description = "Manage the accounts transactions are booked against.")
@RestController
public class AccountController {

    @Autowired
    AccountService accounts;

    @Autowired
    AccountDtoMapper mapper;

    @Operation(summary = "List all accounts")
    @GetMapping("/accounts")
    public List<AccountResponseDto> index() {
        return accounts.listAccounts().stream().map(mapper::toResponse).toList();
    }

    @Operation(summary = "Create a new account")
    @ApiResponse(responseCode = "200", description = "Account created")
    @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    @PostMapping("/accounts")
    public AccountResponseDto create(@Valid @RequestBody AccountRequestDto request) {
        return mapper.toResponse(accounts.createAccount(mapper.toDomain(request)));
    }

    @Operation(summary = "Update an existing account")
    @ApiResponse(responseCode = "200", description = "Account updated")
    @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    @PutMapping("/accounts/{id}")
    public AccountResponseDto update(@PathVariable Long id,
                                     @Valid @RequestBody AccountRequestDto request)
            throws ResourceNotFoundException {
        return mapper.toResponse(accounts.updateAccount(id, mapper.toDomain(request)));
    }

    @Operation(summary = "Delete an account")
    @ApiResponse(responseCode = "200", description = "Account deleted")
    @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    @DeleteMapping("/accounts/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) throws ResourceNotFoundException {
        accounts.deleteAccount(id);
        return new SimpleMessageResponse("Account deleted");
    }
}
