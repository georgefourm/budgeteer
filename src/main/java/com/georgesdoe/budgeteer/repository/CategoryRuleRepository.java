package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.rules.CategoryRule;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRuleRepository extends PagingAndSortingRepository<CategoryRule, Long> {
}
