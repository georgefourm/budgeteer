package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.common.ResourceNotFoundException;
import com.georgesdoe.budgeteer.domain.expense.Expense;
import com.georgesdoe.budgeteer.domain.expense.ExpenseService;
import com.georgesdoe.budgeteer.web.request.ExpenseRequest;
import com.georgesdoe.budgeteer.web.response.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseService expenses;

    @PostMapping("/expenses")
    public Expense create(@Valid @RequestBody ExpenseRequest request) throws ResourceNotFoundException {
        return expenses.createExpense(request);
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
