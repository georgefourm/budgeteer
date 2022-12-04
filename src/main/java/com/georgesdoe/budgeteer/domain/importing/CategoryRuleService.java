package com.georgesdoe.budgeteer.domain.importing;

import com.georgesdoe.budgeteer.repository.CategoryRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CategoryRuleService {
    Iterable<CategoryRule> rules;

    @Autowired
    public CategoryRuleService(CategoryRuleRepository repo) {
        this.rules = repo.findAll(Sort.by(Sort.Direction.DESC, "importance"));
    }

    public CategoryRule findApplicableRule(ImportedTransaction transaction) {
        for (var rule : rules) {
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
