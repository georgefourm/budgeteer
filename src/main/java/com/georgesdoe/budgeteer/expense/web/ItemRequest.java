package com.georgesdoe.budgeteer.expense.web;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemRequest {
    String name;
    BigDecimal defaultCost;
    Long categoryId;
}
