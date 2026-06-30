package com.georgesdoe.budgeteer.rules.repository;

import com.georgesdoe.budgeteer.category.repository.CategoryEntityMapper;
import com.georgesdoe.budgeteer.rules.domain.CategoryRule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CategoryEntityMapper.class)
public interface CategoryRuleEntityMapper {
    CategoryRule toDomain(CategoryRuleEntity entity);

    CategoryRuleEntity toEntity(CategoryRule rule);
}
