package com.georgesdoe.budgeteer.rules.repository;

import com.georgesdoe.budgeteer.common.repository.BaseRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRuleRepository extends BaseRepository<CategoryRuleEntity, Long>, PagingAndSortingRepository<CategoryRuleEntity, Long> {
}
