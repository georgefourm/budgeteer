package com.georgesdoe.budgeteer.income.web;

import lombok.Data;

@Data
public class IncomeTypeRequest {
    String name;
    Long parentId;
}
