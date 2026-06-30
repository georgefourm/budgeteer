package com.georgesdoe.budgeteer.rules.domain;

import com.georgesdoe.budgeteer.category.domain.Category;
import com.georgesdoe.budgeteer.rules.repository.CategoryRuleEntityMapper;
import com.georgesdoe.budgeteer.rules.repository.CategoryRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CategoryRuleService {

    List<CategoryRule> ruleList;

    @Autowired
    public CategoryRuleService(CategoryRuleRepository repo, CategoryRuleEntityMapper mapper) {
        var rules = new ArrayList<CategoryRule>();
        repo.findAll().forEach(entity -> rules.add(mapper.toDomain(entity)));
        this.ruleList = rules;
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
