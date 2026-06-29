package com.georgesdoe.budgeteer.expense.web;

import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.expense.domain.Expense;
import com.georgesdoe.budgeteer.expense.domain.ExpenseService;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseService expenses;

    @GetMapping("/expenses")
    public Iterable<Expense> all() {
        return expenses.listExpenses();
    }

    @PostMapping("/expenses")
    public Expense create(@Valid @RequestBody ExpenseRequest request) throws ResourceNotFoundException {
        return expenses.createExpense(request);
    }

    @PostMapping("/expenses/bulk")
    public SimpleMessageResponse createBulk(@Valid @RequestBody List<ExpenseRequest> request) throws ResourceNotFoundException {
        expenses.createExpenses(request);
        return new SimpleMessageResponse("Expenses saved");
    }

    @PutMapping("/expenses/{id}")
    public Expense update(@PathVariable Long id, @RequestBody ExpenseRequest request) throws ResourceNotFoundException {
        return expenses.updateExpense(id, request);
    }

    @DeleteMapping("/expenses/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) throws ResourceNotFoundException {
        expenses.deleteExpense(id);
        return new SimpleMessageResponse("Expense deleted");
    }
}
