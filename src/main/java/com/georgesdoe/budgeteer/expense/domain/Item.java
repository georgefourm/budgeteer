package com.georgesdoe.budgeteer.expense.domain;

import com.georgesdoe.budgeteer.category.domain.Category;
import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    BigDecimal defaultCost;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
