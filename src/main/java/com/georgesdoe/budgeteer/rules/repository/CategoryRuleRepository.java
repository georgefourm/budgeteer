package com.georgesdoe.budgeteer.rules.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRuleRepository extends CrudRepository<CategoryRuleEntity, Long>, PagingAndSortingRepository<CategoryRuleEntity, Long> {
}
