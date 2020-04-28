package com.georgesdoe.budgeteer.web.response;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class DashboardStatsResponse {
    @NotNull
    BigDecimal totalIncome,totalExpenses;

    public BigDecimal getBalance(){
        return totalIncome.subtract(totalExpenses);
    }
}
