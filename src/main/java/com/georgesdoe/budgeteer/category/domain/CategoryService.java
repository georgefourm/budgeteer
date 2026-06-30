package com.georgesdoe.budgeteer.category.domain;

import com.georgesdoe.budgeteer.category.repository.CategoryEntity;
import com.georgesdoe.budgeteer.category.repository.CategoryEntityMapper;
import com.georgesdoe.budgeteer.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categories;

    @Autowired
    CategoryEntityMapper mapper;

    public List<Category> listCategories() {
        var result = new LinkedList<Category>();
        categories.findAll().forEach(entity -> result.add(mapper.toDomain(entity)));
        return result;
    }

    public Category createCategory(Category category) {
        var entity = mapper.toEntity(category);
        entity.setParent(resolveParent(category));
        categories.save(entity);
        return mapper.toDomain(entity);
    }

    public Category updateCategory(Long id, Category changes) {
        var entity = categories.findByIdOrThrow(id);
        entity.setName(changes.getName());
        entity.setParent(resolveParent(changes));
        categories.save(entity);
        return mapper.toDomain(entity);
    }

    public void deleteCategory(Long id) {
        var entity = categories.findByIdOrThrow(id);
        categories.delete(entity);
    }

    private CategoryEntity resolveParent(Category category) {
        if (category.getParent() == null || category.getParent().getId() == null) {
            return null;
        }
        return categories.findByIdOrThrow(category.getParent().getId());
    }
}
