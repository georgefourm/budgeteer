package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.Income;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface IncomeRepository extends CrudRepository<Income,Long> {

    @Query("SELECT SUM(i.amount) FROM Income i")
    BigDecimal getTotalIncome();
}
