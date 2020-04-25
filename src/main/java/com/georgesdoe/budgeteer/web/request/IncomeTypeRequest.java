package com.georgesdoe.budgeteer.web.request;

import lombok.Data;

@Data
public class IncomeTypeRequest {
    String name;
    Long parentId;
}
