package com.georgesdoe.budgeteer.expense.repository;

import com.georgesdoe.budgeteer.expense.domain.ExpenseList;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseListRepository extends CrudRepository<ExpenseList, Long> {
}
