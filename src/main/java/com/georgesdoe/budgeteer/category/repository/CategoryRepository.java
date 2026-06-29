package com.georgesdoe.budgeteer.category.repository;

import com.georgesdoe.budgeteer.category.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
