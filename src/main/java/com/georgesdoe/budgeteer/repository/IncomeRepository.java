package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.income.Income;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public interface IncomeRepository extends CrudRepository<Income,Long> {

    @Query("SELECT COALESCE(SUM(i.amount),0) " +
            "FROM Income i " +
            "WHERE i.receivedAt >= :start AND i.receivedAt <= :end")
    BigDecimal getTotalIncome(OffsetDateTime start, OffsetDateTime end);

    @Query(nativeQuery = true)
    List<MonthlyIncome> getIncomeByMonth(OffsetDateTime start, OffsetDateTime end);

    interface MonthlyIncome{
        String getDate();

        BigDecimal getAmount();
    }
}
