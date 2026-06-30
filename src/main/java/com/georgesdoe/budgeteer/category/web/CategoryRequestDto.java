package com.georgesdoe.budgeteer.category.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Payload for creating or updating a category.")
@Data
public class CategoryRequestDto {
    @Schema(description = "Display name of the category.", example = "Groceries")
    String name;

    @Schema(
            description = "Identifier of the parent category, or null for a top-level category.",
            example = "3",
            nullable = true
    )
    Long parentId;
}
