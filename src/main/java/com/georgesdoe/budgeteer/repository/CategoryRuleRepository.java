package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.rules.CategoryRule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRuleRepository extends CrudRepository<CategoryRule, Long>, PagingAndSortingRepository<CategoryRule, Long> {
}
