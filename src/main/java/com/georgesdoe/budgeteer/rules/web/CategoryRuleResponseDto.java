package com.georgesdoe.budgeteer.rules.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "A rule that classifies transactions into a category during import.")
@Data
public class CategoryRuleResponseDto {
    @Schema(description = "Unique identifier of the rule.", example = "5")
    Long id;

    @Schema(
            description = "Regular expression matched against a transaction's description.",
            example = "(?i)amazon",
            nullable = true
    )
    String ruleRegex;

    @Schema(description = "Relative priority of the rule; higher values take precedence.", example = "1", nullable = true)
    Integer importance;

    @Schema(
            description = "Identifier of the category assigned when the rule matches, or null.",
            example = "7",
            nullable = true
    )
    Long categoryId;
}
