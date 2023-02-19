package com.georgesdoe.budgeteer.domain.rules;

import com.georgesdoe.budgeteer.domain.common.Category;
import com.georgesdoe.budgeteer.repository.CategoryRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CategoryRuleService {
    CategoryRuleRepository rules;

    Iterable<CategoryRule> ruleList;

    @Autowired
    public CategoryRuleService(CategoryRuleRepository repo) {
        this.rules = repo;
        ruleList = rules.findAll();
    }

    public Optional<Category> getCategoryByDescription(String description) {
        for (var rule : ruleList) {
            if (rule.getDescriptionRule() != null) {
                var pattern = Pattern.compile(rule.getDescriptionRule());
                if (pattern.matcher(description).find()) {
                    return Optional.ofNullable(rule.getCategory());
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Category> getCategoryByName(String name) {
        for (var rule : ruleList) {
            if (rule.getCategoryRule() != null) {
                var pattern = Pattern.compile(rule.getCategoryRule());
                if (pattern.matcher(name).find()) {
                    return Optional.ofNullable(rule.getCategory());
                }
            }
        }
        return Optional.empty();
    }
}
