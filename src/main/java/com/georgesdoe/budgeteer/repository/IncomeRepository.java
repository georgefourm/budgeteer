package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.Income;
import org.springframework.data.repository.CrudRepository;

public interface IncomeRepository extends CrudRepository<Income,Long> {
}
