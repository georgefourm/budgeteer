package com.georgesdoe.budgeteer.rules.repository;

import com.georgesdoe.budgeteer.rules.domain.CategoryRule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRuleRepository extends CrudRepository<CategoryRule, Long>, PagingAndSortingRepository<CategoryRule, Long> {
}
