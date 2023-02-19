package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.common.ResourceNotFoundException;
import com.georgesdoe.budgeteer.domain.expense.Expense;
import com.georgesdoe.budgeteer.domain.expense.ExpenseService;
import com.georgesdoe.budgeteer.web.request.ExpenseRequest;
import com.georgesdoe.budgeteer.web.response.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
