package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense,Long> {

    @Query("SELECT COALESCE(SUM(e.cost),0) " +
            "FROM Expense e " +
            "WHERE e.boughtAt >= :start AND e.boughtAt <= :end")
    BigDecimal getTotalExpenses(OffsetDateTime start, OffsetDateTime end);

    @Query(nativeQuery = true)
    List<CategoryBreakdown> getBreakdownByCategory();

    interface CategoryBreakdown{
        BigDecimal getTotalCost();

        String getCategory();
    }

    @Query(nativeQuery = true)
    List<ExpenseRepository.MonthlyIncome> getExpensesByMonth(OffsetDateTime start, OffsetDateTime end);

    interface MonthlyIncome{
        String getDate();

        BigDecimal getAmount();
    }
}
