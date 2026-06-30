package com.georgesdoe.budgeteer.transaction.web;

import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import com.georgesdoe.budgeteer.transaction.domain.TransactionService;
import com.georgesdoe.budgeteer.transaction.domain.TransactionService.Direction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Transactions", description = "Record and query income and expense transactions.")
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactions;

    @Autowired
    TransactionDtoMapper mapper;

    @Operation(summary = "List transactions, optionally filtered by direction",
            description = "Returns transactions sorted by transaction date descending. "
                    + "When 'direction' is omitted, both incomes and expenses are returned.")
    @GetMapping("/transactions")
    public List<TransactionResponseDto> all(
            @Parameter(description = "Restrict results to incomes or expenses. Omit for all transactions.")
            @RequestParam(required = false) Direction direction) {
        return transactions.listTransactions(direction).stream().map(mapper::toResponse).toList();
    }

    @Operation(summary = "Create a new transaction")
    @ApiResponse(responseCode = "200", description = "Transaction created")
    @ApiResponse(responseCode = "404", description = "Referenced account or category not found", content = @Content)
    @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    @PostMapping("/transactions")
    public TransactionResponseDto create(@Valid @RequestBody TransactionRequestDto request)
            throws ResourceNotFoundException {
        return mapper.toResponse(transactions.createTransaction(mapper.toDomain(request)));
    }

    @Operation(summary = "Create multiple transactions in a single request")
    @ApiResponse(responseCode = "200", description = "Transactions saved")
    @ApiResponse(responseCode = "404", description = "A referenced account or category was not found", content = @Content)
    @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    @PostMapping("/transactions/bulk")
    public SimpleMessageResponse createBulk(@Valid @RequestBody List<TransactionRequestDto> request)
            throws ResourceNotFoundException {
        transactions.createTransactions(request.stream().map(mapper::toDomain).toList());
        return new SimpleMessageResponse("Transactions saved");
    }

    @Operation(summary = "Update an existing transaction")
    @ApiResponse(responseCode = "200", description = "Transaction updated")
    @ApiResponse(responseCode = "404", description = "Transaction, account or category not found", content = @Content)
    @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content)
    @PutMapping("/transactions/{id}")
    public TransactionResponseDto update(@PathVariable Long id, @Valid @RequestBody TransactionRequestDto request)
            throws ResourceNotFoundException {
        return mapper.toResponse(transactions.updateTransaction(id, mapper.toDomain(request)));
    }

    @Operation(summary = "Delete a transaction")
    @ApiResponse(responseCode = "200", description = "Transaction deleted")
    @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content)
    @DeleteMapping("/transactions/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) throws ResourceNotFoundException {
        transactions.deleteTransaction(id);
        return new SimpleMessageResponse("Transaction deleted");
    }
}
