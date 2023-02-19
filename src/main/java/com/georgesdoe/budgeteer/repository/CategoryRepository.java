package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.common.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
