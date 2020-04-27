package com.georgesdoe.budgeteer.web.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DashboardStatsResponse {
    BigDecimal totalIncome,totalExpenses;
}
