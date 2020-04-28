package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.Income;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface IncomeRepository extends CrudRepository<Income,Long> {

    @Query("SELECT COALESCE(SUM(i.amount),0) " +
            "FROM Income i " +
            "WHERE i.receivedAt >= :start AND i.receivedAt <= :end")
    BigDecimal getTotalIncome(OffsetDateTime start, OffsetDateTime end);
}
