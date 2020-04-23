package com.georgesdoe.budgeteer.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "income_types")
public class IncomeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    IncomeType parent;
}
