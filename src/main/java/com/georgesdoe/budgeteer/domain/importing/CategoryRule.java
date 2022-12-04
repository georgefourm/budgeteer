package com.georgesdoe.budgeteer.domain.importing;

import com.georgesdoe.budgeteer.domain.expense.Category;
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