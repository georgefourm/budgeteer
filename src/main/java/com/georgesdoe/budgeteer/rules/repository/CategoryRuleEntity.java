package com.georgesdoe.budgeteer.rules.repository;

import com.georgesdoe.budgeteer.category.repository.CategoryEntity;
import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "category_rules")
public class CategoryRuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String descriptionRule;

    String categoryRule;

    String descriptionReplacement;

    Integer importance;

    @ManyToOne
    @JoinColumn(name = "category_id")
    CategoryEntity category;
}
