package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.Category;
import com.georgesdoe.budgeteer.repository.CategoryRepository;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categories;

    @GetMapping("/categories")
    public Iterable<Category> index() {
        return categories.findAll();
    }

    @PostMapping("/categories")
    public Category create(@RequestParam String name,
                           @RequestParam(required = false) Long parentId) {
        Category category = new Category();
        category.setName(name);
        if (parentId != null) {
            Category parent = categories.findById(parentId)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            category.setParent(parent);
        }
        categories.save(category);
        return category;
    }

    @PutMapping("/categories/{id}")
    public Category update(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam(required = false) Long parentId) {
        Category category = categories.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        category.setName(name);
        if (parentId != null) {
            Category parent = categories.findById(parentId)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            category.setParent(parent);
        }else{
            category.setParent(null);
        }
        categories.save(category);
        return category;
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable Long id) {
        Category category = categories.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        categories.delete(category);
    }
}
