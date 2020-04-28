package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.repository.ExpenseRepository;
import com.georgesdoe.budgeteer.repository.IncomeRepository;
import com.georgesdoe.budgeteer.web.response.DashboardStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class DashboardController {

    @Autowired
    ExpenseRepository expenses;

    @Autowired
    IncomeRepository incomes;

    @GetMapping("/dashboard/balance")
    public DashboardStatsResponse getStats(@RequestParam
                                           @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
                                           OffsetDateTime startDate,
                                           @RequestParam
                                           @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
                                           OffsetDateTime endDate){
        DashboardStatsResponse response = new DashboardStatsResponse();
        response.setTotalExpenses(expenses.getTotalExpenses(startDate,endDate));
        response.setTotalIncome(incomes.getTotalIncome(startDate,endDate));
        return response;
    }

}
