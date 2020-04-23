package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.Income;
import com.georgesdoe.budgeteer.domain.IncomeType;
import com.georgesdoe.budgeteer.repository.IncomeRepository;
import com.georgesdoe.budgeteer.repository.IncomeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@RestController
public class IncomeController {

    @Autowired
    IncomeRepository incomes;

    @Autowired
    IncomeTypeRepository incomeTypes;

    @GetMapping("/incomes")
    public Iterable<Income> index() {
        return incomes.findAll();
    }

    @PostMapping("/incomes")
    public Income create(@RequestParam BigDecimal amount,
                         @RequestParam(required = false) String notes,
                         @RequestParam
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime receivedAt,
                         @RequestParam(required = false) Long typeId) {
        Income income = new Income();
        income.setAmount(amount);
        income.setNotes(notes);
        income.setReceivedAt(receivedAt);
        if (typeId != null){
            IncomeType type = incomeTypes.findById(typeId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            income.setType(type);
        }
        incomes.save(income);
        return income;
    }

    @PutMapping("/incomes/{id}")
    public Income update(
            @PathVariable Long id,
            @RequestParam BigDecimal amount,
            @RequestParam(required = false) String notes,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime receivedAt,
            @RequestParam(required = false) Long typeId) {
        Income income = incomes.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        income.setAmount(amount);
        income.setNotes(notes);
        income.setReceivedAt(receivedAt);
        if (typeId != null){
            IncomeType type = incomeTypes.findById(typeId)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            income.setType(type);
        }else{
            income.setType(null);
        }
        incomes.save(income);
        return income;
    }

    @DeleteMapping("/incomes/{id}")
    public void delete(@PathVariable Long id) {
        Income income = incomes.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        incomes.delete(income);
    }
}
