package com.georgesdoe.budgeteer.transaction.web;

import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import com.georgesdoe.budgeteer.transaction.domain.TransactionService;
import com.georgesdoe.budgeteer.transaction.domain.TransactionService.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactions;

    @Autowired
    TransactionDtoMapper mapper;

    @GetMapping("/transactions")
    public List<TransactionResponseDto> all(@RequestParam(required = false) Direction direction) {
        return transactions.listTransactions(direction).stream().map(mapper::toResponse).toList();
    }

    @PostMapping("/transactions")
    public TransactionResponseDto create(@Valid @RequestBody TransactionRequestDto request)
            throws ResourceNotFoundException {
        return mapper.toResponse(transactions.createTransaction(mapper.toDomain(request)));
    }

    @PostMapping("/transactions/bulk")
    public SimpleMessageResponse createBulk(@Valid @RequestBody List<TransactionRequestDto> request)
            throws ResourceNotFoundException {
        transactions.createTransactions(request.stream().map(mapper::toDomain).toList());
        return new SimpleMessageResponse("Transactions saved");
    }

    @PutMapping("/transactions/{id}")
    public TransactionResponseDto update(@PathVariable Long id, @Valid @RequestBody TransactionRequestDto request)
            throws ResourceNotFoundException {
        return mapper.toResponse(transactions.updateTransaction(id, mapper.toDomain(request)));
    }

    @DeleteMapping("/transactions/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) throws ResourceNotFoundException {
        transactions.deleteTransaction(id);
        return new SimpleMessageResponse("Transaction deleted");
    }
}
