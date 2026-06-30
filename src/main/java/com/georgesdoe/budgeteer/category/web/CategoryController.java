package com.georgesdoe.budgeteer.category.web;

import com.georgesdoe.budgeteer.category.domain.CategoryService;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categories", description = "Manage the hierarchical categories used to classify transactions.")
@RestController
public class CategoryController {

    @Autowired
    CategoryService categories;

    @Autowired
    CategoryDtoMapper mapper;

    @Operation(summary = "List all categories")
    @GetMapping("/categories")
    public List<CategoryResponseDto> index() {
        return categories.listCategories().stream().map(mapper::toResponse).toList();
    }

    @Operation(summary = "Create a new category")
    @ApiResponse(responseCode = "200", description = "Category created")
    @ApiResponse(responseCode = "404", description = "Parent category not found", content = @Content)
    @PostMapping("/categories")
    public CategoryResponseDto create(@RequestBody CategoryRequestDto request) {
        return mapper.toResponse(categories.createCategory(mapper.toDomain(request)));
    }

    @Operation(summary = "Update an existing category")
    @ApiResponse(responseCode = "200", description = "Category updated")
    @ApiResponse(responseCode = "404", description = "Category or parent category not found", content = @Content)
    @PutMapping("/categories/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto request) {
        return mapper.toResponse(categories.updateCategory(id, mapper.toDomain(request)));
    }

    @Operation(summary = "Delete a category")
    @ApiResponse(responseCode = "200", description = "Category deleted")
    @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    @DeleteMapping("/categories/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) {
        categories.deleteCategory(id);
        return new SimpleMessageResponse("Category deleted");
    }
}
