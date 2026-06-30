package com.georgesdoe.budgeteer.transaction.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Schema(description = "Payload for creating or updating a transaction.")
@Data
public class TransactionRequestDto {
    @Schema(
            description = "Identifier of the category to classify the transaction under, or null if uncategorized.",
            example = "7",
            nullable = true
    )
    Long categoryId;

    @Schema(description = "Identifier of the account the transaction belongs to.", example = "1")
    @NotNull(message = "The account the transaction belongs to must be provided")
    Long accountId;

    @Schema(
            description = "Signed amount: positive values are incomes, negative values are expenses.",
            example = "-42.50"
    )
    @NotNull(message = "Amount must be provided (positive for income, negative for expense)")
    BigDecimal amount;

    @Schema(description = "Free-text description of the transaction.", example = "Weekly grocery shopping")
    String description;

    @Schema(description = "Instant the transaction took place.", example = "2026-06-30T10:15:30Z")
    @NotNull(message = "The date of the transaction must be provided")
    Instant transactionTs;
}
