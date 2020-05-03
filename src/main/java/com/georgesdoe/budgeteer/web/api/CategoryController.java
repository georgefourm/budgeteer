package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.expense.Category;
import com.georgesdoe.budgeteer.repository.CategoryRepository;
import com.georgesdoe.budgeteer.web.request.CategoryRequest;
import com.georgesdoe.budgeteer.web.response.SimpleMessageResponse;
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
    public Category create(@RequestBody CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        if (request.getParentId() != null) {
            Category parent = categories.findById(request.getParentId())
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            category.setParent(parent);
        }
        categories.save(category);
        return category;
    }

    @PutMapping("/categories/{id}")
    public Category update(@PathVariable Long id,
                           @RequestBody CategoryRequest request) {
        Category category = categories.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        category.setName(request.getName());
        Long parentId = request.getParentId();
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
    public SimpleMessageResponse delete(@PathVariable Long id) {
        Category category = categories.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        categories.delete(category);
        return new SimpleMessageResponse("Category deleted");
    }
}
