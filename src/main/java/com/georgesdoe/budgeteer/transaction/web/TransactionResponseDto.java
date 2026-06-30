package com.georgesdoe.budgeteer.transaction.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Schema(description = "A recorded transaction. A positive amount is income, a negative amount is an expense.")
@Data
public class TransactionResponseDto {
    @Schema(description = "Unique identifier of the transaction.", example = "100")
    Long id;

    @Schema(
            description = "Signed amount: positive values are incomes, negative values are expenses.",
            example = "-42.50"
    )
    BigDecimal amount;

    @Schema(description = "Free-text description of the transaction.", example = "Weekly grocery shopping")
    String description;

    @Schema(description = "Instant the transaction took place.", example = "2026-06-30T10:15:30Z")
    Instant transactionTs;

    @Schema(description = "Identifier of the account the transaction belongs to.", example = "1")
    Long accountId;

    @Schema(
            description = "Identifier of the category the transaction is classified under, or null if uncategorized.",
            example = "7",
            nullable = true
    )
    Long categoryId;

    @Schema(description = "Instant the transaction was recorded in the system.", example = "2026-06-30T10:16:00Z")
    Instant createdAt;
}
