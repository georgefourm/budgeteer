package com.georgesdoe.budgeteer.transaction.web;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionRequest {
    Long categoryId;

    @NotNull(message = "The account the transaction belongs to must be provided")
    Long accountId;

    @NotNull(message = "Amount must be provided (positive for income, negative for expense)")
    BigDecimal amount;

    String description;

    @NotNull(message = "The date of the transaction must be provided")
    Instant transactionTs;
}
