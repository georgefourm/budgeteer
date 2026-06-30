package com.georgesdoe.budgeteer.category.domain;

import com.georgesdoe.budgeteer.category.repository.CategoryEntityMapper;
import com.georgesdoe.budgeteer.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Resolves the {@link Category} that should be assigned to a transaction, either from its raw
 * category name (case-insensitive exact match) or from its description (matched against category
 * rule regexes).
 */
@Service
public class CategoryResolver {

    private final CategoryRepository categories;
    private final CategoryEntityMapper mapper;

    @Autowired
    public CategoryResolver(CategoryRepository categories, CategoryEntityMapper mapper) {
        this.categories = categories;
        this.mapper = mapper;
    }

    public Optional<Category> resolveByName(String name) {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }
        return categories.findFirstByNameIgnoreCase(name).map(mapper::toDomain);
    }

    public Optional<Category> resolveByDescription(String description) {
        if (description == null || description.isBlank()) {
            return Optional.empty();
        }
        return categories.findFirstMatchingRule(description).map(mapper::toDomain);
    }
}
