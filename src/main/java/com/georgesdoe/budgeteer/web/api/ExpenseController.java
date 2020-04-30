package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.Expense;
import com.georgesdoe.budgeteer.domain.Item;
import com.georgesdoe.budgeteer.repository.ExpenseRepository;
import com.georgesdoe.budgeteer.repository.ItemRepository;
import com.georgesdoe.budgeteer.web.request.ExpenseRequest;
import com.georgesdoe.budgeteer.web.response.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    ItemRepository items;

    @Autowired
    ExpenseRepository expenses;

    @GetMapping("/expenses")
    public Iterable<Expense> index() {
        return expenses.findAll();
    }

    @GetMapping("/expenses/breakdown")
    public List<ExpenseRepository.CategoryBreakdown> breakdown(){
        return expenses.getBreakdownByCategory();
    }

    @GetMapping("/expenses/monthly")
    public List<ExpenseRepository.MonthlyIncome> monthly(@RequestParam
                                                        @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
                                                                OffsetDateTime startDate,
                                                        @RequestParam
                                                        @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
                                                                OffsetDateTime endDate){
        return expenses.getExpensesByMonth(startDate,endDate);
    }

    @PostMapping("/expenses")
    public Expense create(@RequestBody ExpenseRequest request) {
        Expense expense = new Expense();
        Item item = items.findById(request.getItemId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        expense.setItem(item);
        expense.setCost(request.getCost());
        expense.setAmount(request.getAmount());
        expense.setBoughtAt(request.getBoughtAt());
        expenses.save(expense);
        return expense;
    }

    @PutMapping("/expenses/{id}")
    public Expense update(@PathVariable Long id,@RequestBody ExpenseRequest request) {
        Expense expense = expenses.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Item item = items.findById(request.getItemId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        expense.setItem(item);
        expense.setCost(request.getCost());
        expense.setAmount(request.getAmount());
        expense.setBoughtAt(request.getBoughtAt());
        expenses.save(expense);
        return expense;
    }

    @DeleteMapping("/expenses/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) {
        Expense expense = expenses.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        expenses.delete(expense);
        return new SimpleMessageResponse("Expense deleted");
    }
}
