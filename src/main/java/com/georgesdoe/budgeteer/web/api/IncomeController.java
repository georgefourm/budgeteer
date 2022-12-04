package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.common.ResourceNotFoundException;
import com.georgesdoe.budgeteer.domain.expense.Category;
import com.georgesdoe.budgeteer.domain.income.Income;
import com.georgesdoe.budgeteer.repository.CategoryRepository;
import com.georgesdoe.budgeteer.repository.IncomeRepository;
import com.georgesdoe.budgeteer.web.request.IncomeRequest;
import com.georgesdoe.budgeteer.web.response.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class IncomeController {

    @Autowired
    IncomeRepository incomes;

    @Autowired
    CategoryRepository categories;

    @GetMapping("/incomes")
    public Iterable<Income> index() {
        return incomes.findAll();
    }

    @GetMapping("/incomes/monthly")
    public List<IncomeRepository.MonthlyIncome> monthly(@RequestParam
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                        OffsetDateTime startDate,
                                                        @RequestParam
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                        OffsetDateTime endDate) {
        return incomes.getIncomeByMonth(startDate, endDate);
    }

    @PostMapping("/incomes")
    public Income create(@RequestBody IncomeRequest request) throws ResourceNotFoundException {
        Income income = new Income();
        income.setAmount(request.getAmount());
        income.setDescription(request.getDescription());
        income.setReceivedAt(request.getReceivedAt());
        Long categoryId = request.getCategoryId();
        if (categoryId != null) {
            var category = categories.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(Category.class));
            income.setCategory(category);
        }
        incomes.save(income);
        return income;
    }

    @PutMapping("/incomes/{id}")
    public Income update(@PathVariable Long id, @RequestBody IncomeRequest request) throws ResourceNotFoundException {
        Income income = incomes.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        income.setAmount(request.getAmount());
        income.setDescription(request.getDescription());
        income.setReceivedAt(request.getReceivedAt());
        Long categoryId = request.getCategoryId();
        if (categoryId != null) {
            var category = categories.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(Category.class));
            income.setCategory(category);
        }
        incomes.save(income);
        return income;
    }

    @DeleteMapping("/incomes/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) {
        Income income = incomes.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        incomes.delete(income);
        return new SimpleMessageResponse("Income deleted");
    }
}
