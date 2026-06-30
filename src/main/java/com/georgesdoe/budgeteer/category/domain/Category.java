package com.georgesdoe.budgeteer.category.domain;

import lombok.Data;

@Data
public class Category {
    Long id;

    String name;

    Category parent;
}
