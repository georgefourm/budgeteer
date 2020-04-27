package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface ExpenseRepository extends CrudRepository<Expense,Long> {

    @Query("SELECT SUM(e.cost) FROM Expense e")
    BigDecimal getTotalExpenses();
}
