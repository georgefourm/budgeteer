package com.georgesdoe.budgeteer.category.web;

import lombok.Data;

@Data
public class CategoryRequestDto {
    String name;

    Long parentId;
}
