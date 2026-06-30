package com.georgesdoe.budgeteer.account.web;

import com.georgesdoe.budgeteer.account.domain.AccountService;
import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accounts;

    @Autowired
    AccountDtoMapper mapper;

    @GetMapping("/accounts")
    public List<AccountResponseDto> index() {
        return accounts.listAccounts().stream().map(mapper::toResponse).toList();
    }

    @PostMapping("/accounts")
    public AccountResponseDto create(@Valid @RequestBody AccountRequestDto request) {
        return mapper.toResponse(accounts.createAccount(mapper.toDomain(request)));
    }

    @PutMapping("/accounts/{id}")
    public AccountResponseDto update(@PathVariable Long id,
                                     @Valid @RequestBody AccountRequestDto request)
            throws ResourceNotFoundException {
        return mapper.toResponse(accounts.updateAccount(id, mapper.toDomain(request)));
    }

    @DeleteMapping("/accounts/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) throws ResourceNotFoundException {
        accounts.deleteAccount(id);
        return new SimpleMessageResponse("Account deleted");
    }
}
