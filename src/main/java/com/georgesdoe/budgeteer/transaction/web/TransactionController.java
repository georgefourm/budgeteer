package com.georgesdoe.budgeteer.transaction.web;

import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import com.georgesdoe.budgeteer.transaction.domain.Transaction;
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

    @GetMapping("/transactions")
    public List<Transaction> all(@RequestParam(required = false) Direction direction) {
        return transactions.listTransactions(direction);
    }

    @PostMapping("/transactions")
    public Transaction create(@Valid @RequestBody TransactionRequest request) throws ResourceNotFoundException {
        return transactions.createTransaction(request);
    }

    @PostMapping("/transactions/bulk")
    public SimpleMessageResponse createBulk(@Valid @RequestBody List<TransactionRequest> request)
            throws ResourceNotFoundException {
        transactions.createTransactions(request);
        return new SimpleMessageResponse("Transactions saved");
    }

    @PutMapping("/transactions/{id}")
    public Transaction update(@PathVariable Long id, @Valid @RequestBody TransactionRequest request)
            throws ResourceNotFoundException {
        return transactions.updateTransaction(id, request);
    }

    @DeleteMapping("/transactions/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) throws ResourceNotFoundException {
        transactions.deleteTransaction(id);
        return new SimpleMessageResponse("Transaction deleted");
    }
}
