package com.georgesdoe.budgeteer.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExpenseListRequest {

    BigDecimal total;

    LocalDate listDate;

    List<ExpenseDto> expenses = new ArrayList<>();

    @Data
    public static class ExpenseDto {
        @NotNull
        Long itemId;

        @NotNull
        BigDecimal cost;

        Integer amount;
    }
}

