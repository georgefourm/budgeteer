package com.georgesdoe.budgeteer.rules.domain;

import com.georgesdoe.budgeteer.category.domain.Category;
import com.georgesdoe.budgeteer.category.repository.CategoryEntity;
import com.georgesdoe.budgeteer.category.repository.CategoryRepository;
import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.rules.repository.CategoryRuleEntity;
import com.georgesdoe.budgeteer.rules.repository.CategoryRuleEntityMapper;
import com.georgesdoe.budgeteer.rules.repository.CategoryRuleRepository;
import com.georgesdoe.budgeteer.transaction.domain.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryRuleService {

    private final CategoryRuleRepository rules;
    private final CategoryRuleEntityMapper mapper;
    private final CategoryRepository categories;
    private final TransactionService transactions;

    @Autowired
    public CategoryRuleService(CategoryRuleRepository rules,
                               CategoryRuleEntityMapper mapper,
                               CategoryRepository categories,
                               TransactionService transactions) {
        this.rules = rules;
        this.mapper = mapper;
        this.categories = categories;
        this.transactions = transactions;
    }

    public List<CategoryRule> listRules() {
        var result = new ArrayList<CategoryRule>();
        rules.findAll().forEach(entity -> result.add(mapper.toDomain(entity)));
        return result;
    }

    public CategoryRule createRule(CategoryRule rule) throws ResourceNotFoundException {
        var entity = mapper.toEntity(rule);
        entity.setCategory(resolveCategory(rule));
        rules.save(entity);
        return mapper.toDomain(entity);
    }

    public CategoryRule updateRule(Long id, CategoryRule changes) throws ResourceNotFoundException {
        var entity = rules.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CategoryRule.class));
        entity.setRuleRegex(changes.getRuleRegex());
        entity.setImportance(changes.getImportance());
        entity.setCategory(resolveCategory(changes));
        rules.save(entity);
        return mapper.toDomain(entity);
    }

    public void deleteRule(Long id) throws ResourceNotFoundException {
        var entity = rules.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CategoryRule.class));
        rules.delete(entity);
    }

    /**
     * Applies the rule to every existing transaction whose description matches its regex,
     * assigning the rule's category in a single database update. Returns the number of
     * transactions updated.
     */
    public int applyRule(Long id) throws ResourceNotFoundException {
        var entity = rules.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CategoryRule.class));
        return transactions.assignCategoryByDescription(categoryIdOf(entity), entity.getRuleRegex());
    }

    private Long categoryIdOf(CategoryRuleEntity entity) {
        return entity.getCategory() == null ? null : entity.getCategory().getId();
    }

    private CategoryEntity resolveCategory(CategoryRule rule) throws ResourceNotFoundException {
        if (rule.getCategory() == null || rule.getCategory().getId() == null) {
            return null;
        }
        return categories.findById(rule.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Category.class));
    }
}
