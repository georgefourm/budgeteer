package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense,Long> {
}
