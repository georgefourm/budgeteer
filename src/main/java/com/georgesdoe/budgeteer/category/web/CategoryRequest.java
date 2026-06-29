package com.georgesdoe.budgeteer.category.web;

import lombok.Data;

@Data
public class CategoryRequest {
    String name;
    Long parentId;
}
