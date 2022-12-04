package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.importing.CategoryRule;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRuleRepository extends PagingAndSortingRepository<CategoryRule, Long> {
}
