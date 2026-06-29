package com.georgesdoe.budgeteer.category.domain;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Category parent;
}
