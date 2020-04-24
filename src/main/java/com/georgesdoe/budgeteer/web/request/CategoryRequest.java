package com.georgesdoe.budgeteer.web.request;

import lombok.Data;

@Data
public class CategoryRequest {
    String name;
    Long parentId;
}
