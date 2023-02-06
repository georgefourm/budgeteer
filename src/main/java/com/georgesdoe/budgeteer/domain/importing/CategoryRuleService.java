package com.georgesdoe.budgeteer.domain.importing;

import com.georgesdoe.budgeteer.domain.common.ResourceNotFoundException;
import com.georgesdoe.budgeteer.repository.CategoryRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CategoryRuleService {
    CategoryRuleRepository rules;

    @Autowired
    public CategoryRuleService(CategoryRuleRepository repo) {
        this.rules = repo;
    }

    public void applyRule(Long ruleId) throws ResourceNotFoundException {
        var rule = rules.findById(ruleId).orElseThrow(() -> new ResourceNotFoundException(CategoryRule.class));

    }

    public CategoryRule findApplicableRule(ImportedTransaction transaction) {
        for (var rule : rules.findAll()) {
            if (transaction.getCategory() != null && rule.getCategoryRule() != null) {
                var pattern = Pattern.compile(rule.getCategoryRule());
                if (pattern.matcher(transaction.getCategory()).find()) {
                    return rule;
                }
            }
            if (transaction.getDescription() != null && rule.getDescriptionRule() != null) {
                var pattern = Pattern.compile(rule.getDescriptionRule());
                if (pattern.matcher(transaction.getDescription()).find()) {
                    return rule;
                }
            }
        }
        return null;
    }
}
