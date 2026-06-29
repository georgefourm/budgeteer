package com.georgesdoe.budgeteer.rules.domain;

import com.georgesdoe.budgeteer.category.domain.Category;
import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "category_rules")
public class CategoryRule {
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
    Category category;
}