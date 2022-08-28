package com.georgesdoe.budgeteer.domain.expense;

import com.georgesdoe.budgeteer.domain.common.ResourceNotFoundException;
import com.georgesdoe.budgeteer.repository.CategoryRepository;
import com.georgesdoe.budgeteer.repository.ExpenseRepository;
import com.georgesdoe.budgeteer.repository.ItemRepository;
import com.georgesdoe.budgeteer.web.request.ExpenseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenses;

    @Autowired
    ItemRepository items;

    @Autowired
    CategoryRepository categories;

    public Expense createExpense(ExpenseRequest request) throws ResourceNotFoundException {
        var expense = new Expense();

        populateExpense(expense, request);

        expenses.save(expense);

        return expense;
    }

    protected void populateExpense(Expense expense, ExpenseRequest request) throws ResourceNotFoundException {
        expense.setBoughtAt(request.getBoughtAt());
        expense.setAmount(request.getAmount());
        expense.setCost(request.getCost());
        expense.setDescription(request.getDescription());

        if (request.getItemId() != null) {
            var item = items.findById(request.getItemId())
                    .orElseThrow(() -> new ResourceNotFoundException(Item.class));
            expense.setItem(item);
        }
        if (request.getCategoryId() != null) {
            var category = categories.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(Category.class));
            expense.setCategory(category);
        }
    }

    public Expense updateExpense(Long expenseId, ExpenseRequest request) throws ResourceNotFoundException {
        var expense = expenses.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException(Expense.class));
        populateExpense(expense, request);

        expenses.save(expense);

        return expense;
    }

    public void deleteExpense(Long expenseId) throws ResourceNotFoundException {
        var expense = expenses.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException(Expense.class));
        expenses.delete(expense);
    }
}
