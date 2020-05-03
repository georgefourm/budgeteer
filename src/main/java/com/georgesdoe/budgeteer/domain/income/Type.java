package com.georgesdoe.budgeteer.domain.income;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "income_types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Type parent;
}
