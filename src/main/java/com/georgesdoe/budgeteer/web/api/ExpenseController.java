package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.expense.Expense;
import com.georgesdoe.budgeteer.domain.expense.ExpenseList;
import com.georgesdoe.budgeteer.repository.ExpenseListRepository;
import com.georgesdoe.budgeteer.repository.ExpenseRepository;
import com.georgesdoe.budgeteer.repository.ItemRepository;
import com.georgesdoe.budgeteer.web.request.ExpenseListRequest;
import com.georgesdoe.budgeteer.web.response.SimpleMessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {

    @Autowired
    ItemRepository items;

    @Autowired
    ExpenseRepository expenses;

    @Autowired
    ExpenseListRepository expenseLists;

    @GetMapping("/expenses")
    public Iterable<ExpenseList> index() {
        return expenseLists.findAll();
    }

    @GetMapping("/expenses/breakdown")
    public List<ExpenseRepository.CategoryBreakdown> breakdown() {
        return expenses.getBreakdownByCategory();
    }

    @GetMapping("/expenses/monthly")
    public List<ExpenseRepository.MonthlyIncome> monthly(@RequestParam
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                 OffsetDateTime startDate,
                                                         @RequestParam
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                 OffsetDateTime endDate) {
        return expenses.getExpensesByMonth(startDate, endDate);
    }

    @PostMapping("/expenses")
    public ExpenseList create(@RequestBody ExpenseListRequest request) {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(ExpenseListRequest.ExpenseDto.class, Expense.class)
                .addMappings(map -> map.skip(Expense::setId));

        ExpenseList list = mapper.map(request, ExpenseList.class);
        list.updateTotal();
        expenseLists.save(list);
        return list;
    }

    @PutMapping("/expenses/{id}")
    public ExpenseList update(@PathVariable Long id, @RequestBody ExpenseListRequest request) {
        ExpenseList list = expenseLists.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(ExpenseListRequest.ExpenseDto.class, Expense.class)
                .addMappings(map -> map.skip(Expense::setId));

        list.setListDate(request.getListDate());
        list.getExpenses().clear();
        list.getExpenses().addAll(request.getExpenses()
                .stream()
                .map(expenseDto -> mapper.map(expenseDto,Expense.class))
                .collect(Collectors.toList())
        );
        list.updateTotal();
        expenseLists.save(list);
        return list;
    }

    @DeleteMapping("/expenses/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) {
        ExpenseList expense = expenseLists.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        expenseLists.delete(expense);
        return new SimpleMessageResponse("Expense list deleted");
    }
}
