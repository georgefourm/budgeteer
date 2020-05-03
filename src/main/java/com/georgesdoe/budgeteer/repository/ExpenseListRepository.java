package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.expense.ExpenseList;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseListRepository extends CrudRepository<ExpenseList, Long> {
}
