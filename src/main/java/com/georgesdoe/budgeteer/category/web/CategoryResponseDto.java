package com.georgesdoe.budgeteer.category.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "A category used to classify transactions, optionally nested under a parent.")
@Data
public class CategoryResponseDto {
    @Schema(description = "Unique identifier of the category.", example = "7")
    Long id;

    @Schema(description = "Display name of the category.", example = "Groceries")
    String name;

    @Schema(
            description = "Identifier of the parent category, or null for a top-level category.",
            example = "3",
            nullable = true
    )
    Long parentId;
}
