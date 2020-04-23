package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.Expense;
import com.georgesdoe.budgeteer.domain.Item;
import com.georgesdoe.budgeteer.repository.ExpenseRepository;
import com.georgesdoe.budgeteer.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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

    @PostMapping("/expenses")
    public Expense create(@RequestParam Long itemId,
                          @RequestParam BigDecimal cost,
                          @RequestParam(required = false) Integer amount,
                          @RequestParam
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime boughtAt
    ) {
        Expense expense = new Expense();
        Item item = items.findById(itemId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        expense.setItem(item);
        expense.setCost(cost);
        expense.setAmount(amount);
        expense.setBoughtAt(boughtAt);
        expenses.save(expense);
        return expense;
    }

    @PutMapping("/expenses/{id}")
    public Expense update(
            @PathVariable Long id,
            @RequestParam Long itemId,
            @RequestParam BigDecimal cost,
            @RequestParam(required = false) Integer amount,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime boughtAt) {
        Expense expense = expenses.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Item item = items.findById(itemId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        expense.setItem(item);
        expense.setCost(cost);
        expense.setAmount(amount);
        expense.setBoughtAt(boughtAt);
        expenses.save(expense);
        return expense;
    }

    @DeleteMapping("/expenses/{id}")
    public void delete(@PathVariable Long id) {
        Expense expense = expenses.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        expenses.delete(expense);
    }
}
