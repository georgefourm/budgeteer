package com.georgesdoe.budgeteer.transaction.web;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionResponseDto {
    Long id;

    BigDecimal amount;

    String description;

    Instant transactionTs;

    Long accountId;

    Long categoryId;

    Instant createdAt;
}
