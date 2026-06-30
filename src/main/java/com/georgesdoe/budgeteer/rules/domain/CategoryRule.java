package com.georgesdoe.budgeteer.rules.domain;

import com.georgesdoe.budgeteer.category.domain.Category;
import lombok.Data;

@Data
public class CategoryRule {
    Long id;

    String ruleRegex;

    Integer importance;

    Category category;
}
