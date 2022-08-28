package com.georgesdoe.budgeteer.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ExpenseRequest {
    Long itemId;

    Long categoryId;

    @NotNull(message = "Cost must be provided")
    BigDecimal cost;

    String description;

    Integer amount = 1;

    @NotNull(message = "The date of the expense must be provided")
    Instant boughtAt;
}

