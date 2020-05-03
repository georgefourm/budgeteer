package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.expense.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
