package com.georgesdoe.budgeteer.category.web;

import lombok.Data;

@Data
public class CategoryResponseDto {
    Long id;

    String name;

    Long parentId;
}
