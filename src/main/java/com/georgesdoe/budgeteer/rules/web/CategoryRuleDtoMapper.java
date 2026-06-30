package com.georgesdoe.budgeteer.rules.web;

import com.georgesdoe.budgeteer.category.domain.Category;
import com.georgesdoe.budgeteer.rules.domain.CategoryRule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryRuleDtoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId")
    CategoryRule toDomain(CategoryRuleRequestDto dto);

    @Mapping(target = "categoryId", source = "category.id")
    CategoryRuleResponseDto toResponse(CategoryRule rule);

    default Category mapCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        var category = new Category();
        category.setId(categoryId);
        return category;
    }
}
