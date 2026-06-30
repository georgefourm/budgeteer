package com.georgesdoe.budgeteer.category.web;

import com.georgesdoe.budgeteer.category.domain.CategoryService;
import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categories;

    @Autowired
    CategoryDtoMapper mapper;

    @GetMapping("/categories")
    public List<CategoryResponseDto> index() {
        return categories.listCategories().stream().map(mapper::toResponse).toList();
    }

    @PostMapping("/categories")
    public CategoryResponseDto create(@RequestBody CategoryRequestDto request)
            throws ResourceNotFoundException {
        return mapper.toResponse(categories.createCategory(mapper.toDomain(request)));
    }

    @PutMapping("/categories/{id}")
    public CategoryResponseDto update(@PathVariable Long id,
                                      @RequestBody CategoryRequestDto request)
            throws ResourceNotFoundException {
        return mapper.toResponse(categories.updateCategory(id, mapper.toDomain(request)));
    }

    @DeleteMapping("/categories/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) throws ResourceNotFoundException {
        categories.deleteCategory(id);
        return new SimpleMessageResponse("Category deleted");
    }
}
