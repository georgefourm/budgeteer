package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.repository.ExpenseRepository;
import com.georgesdoe.budgeteer.repository.IncomeRepository;
import com.georgesdoe.budgeteer.web.response.DashboardStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @Autowired
    ExpenseRepository expenses;

    @Autowired
    IncomeRepository incomes;

    @GetMapping("/dashboard")
    public DashboardStatsResponse getStats(){
        DashboardStatsResponse response = new DashboardStatsResponse();
        response.setTotalExpenses(expenses.getTotalExpenses());
        response.setTotalIncome(incomes.getTotalIncome());
        return response;
    }

}
