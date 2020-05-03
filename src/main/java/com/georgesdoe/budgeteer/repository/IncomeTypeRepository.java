package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.income.Type;
import org.springframework.data.repository.CrudRepository;

public interface IncomeTypeRepository extends CrudRepository<Type, Long> {
}
