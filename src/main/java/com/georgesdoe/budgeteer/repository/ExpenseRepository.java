package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface ExpenseRepository extends CrudRepository<Expense,Long> {

    @Query("SELECT COALESCE(SUM(e.cost),0) " +
            "FROM Expense e " +
            "WHERE e.boughtAt >= :start AND e.boughtAt <= :end")
    BigDecimal getTotalExpenses(OffsetDateTime start, OffsetDateTime end);
}
