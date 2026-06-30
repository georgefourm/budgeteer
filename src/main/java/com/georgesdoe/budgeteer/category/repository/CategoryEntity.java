package com.georgesdoe.budgeteer.category.repository;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    CategoryEntity parent;
}
