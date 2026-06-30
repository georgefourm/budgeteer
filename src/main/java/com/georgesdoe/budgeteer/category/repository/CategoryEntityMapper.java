package com.georgesdoe.budgeteer.category.repository;

import com.georgesdoe.budgeteer.category.domain.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity toEntity(Category category);

    Category toDomain(CategoryEntity entity);
}
