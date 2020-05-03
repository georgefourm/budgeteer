package com.georgesdoe.budgeteer.domain.expense;

import lombok.Data;

import javax.persistence.*;
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
