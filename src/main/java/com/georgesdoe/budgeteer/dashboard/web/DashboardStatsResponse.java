package com.georgesdoe.budgeteer.dashboard.web;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DashboardStatsResponse {
    BigDecimal totalIncome,totalExpenses;

    public BigDecimal getBalance(){
        return totalIncome.subtract(totalExpenses);
    }
}
