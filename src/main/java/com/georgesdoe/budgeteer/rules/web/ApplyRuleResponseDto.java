package com.georgesdoe.budgeteer.rules.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "Result of applying a category rule to existing transactions.")
@Data
@AllArgsConstructor
public class ApplyRuleResponseDto {
    @Schema(description = "Number of transactions updated by applying the rule.", example = "12")
    int updatedTransactions;
}
