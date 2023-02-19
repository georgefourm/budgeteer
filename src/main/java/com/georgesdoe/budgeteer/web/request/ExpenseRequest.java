package com.georgesdoe.budgeteer.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ExpenseRequest {
    Long categoryId;

    Long groupId;

    Long memberId;

    @NotNull(message = "Cost must be provided")
    BigDecimal amount;

    String description;

    @NotNull(message = "The date of the expense must be provided")
    Instant boughtAt;
}

