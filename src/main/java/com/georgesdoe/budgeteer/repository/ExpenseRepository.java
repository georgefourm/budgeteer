package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.expense.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense,Long> {

    @Query("SELECT COALESCE(SUM(e.total),0) " +
            "FROM ExpenseList e " +
            "WHERE e.listDate >= :start AND e.listDate <= :end")
    BigDecimal getTotalExpenses(LocalDate start, LocalDate end);

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
