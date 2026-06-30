package com.georgesdoe.budgeteer.category.web;

import com.georgesdoe.budgeteer.category.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", source = "parentId")
    Category toDomain(CategoryRequestDto dto);

    @Mapping(target = "parentId", source = "parent.id")
    CategoryResponseDto toResponse(Category category);

    default Category mapParent(Long parentId) {
        if (parentId == null) {
            return null;
        }
        var parent = new Category();
        parent.setId(parentId);
        return parent;
    }
}
