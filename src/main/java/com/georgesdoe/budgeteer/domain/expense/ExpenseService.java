package com.georgesdoe.budgeteer.domain.expense;

import com.georgesdoe.budgeteer.domain.common.Category;
import com.georgesdoe.budgeteer.domain.common.ResourceNotFoundException;
import com.georgesdoe.budgeteer.repository.CategoryRepository;
import com.georgesdoe.budgeteer.repository.ExpenseRepository;
import com.georgesdoe.budgeteer.repository.GroupRepository;
import com.georgesdoe.budgeteer.web.request.ExpenseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenses;

    @Autowired
    CategoryRepository categories;

    @Autowired
    GroupRepository groups;

    public Iterable<Expense> listExpenses() {
        return expenses.findAll(Sort.by(Sort.Direction.DESC, "boughtAt"));
    }

    public Expense createExpense(ExpenseRequest request) throws ResourceNotFoundException {
        var expense = new Expense();

        populateExpense(expense, request);

        expenses.save(expense);

        return expense;
    }

    public void createExpenses(List<ExpenseRequest> request) throws ResourceNotFoundException {
        var expenses = new LinkedList<Expense>();
        for (ExpenseRequest expenseRequest : request) {
            var expense = new Expense();
            populateExpense(expense, expenseRequest);
            expenses.add(expense);
        }

        this.expenses.saveAll(expenses);
    }

    protected void populateExpense(Expense expense, ExpenseRequest request) throws ResourceNotFoundException {
        expense.setBoughtAt(request.getBoughtAt());
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setMemberId(request.getMemberId());
        expense.setGroupId(request.getGroupId());

        if (request.getCategoryId() != null) {
            categories.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(Category.class));
            expense.setCategoryId(request.getCategoryId());
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
