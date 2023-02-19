package com.georgesdoe.budgeteer.domain.rules;

import com.georgesdoe.budgeteer.domain.common.Category;
import lombok.Data;

import javax.persistence.*;

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