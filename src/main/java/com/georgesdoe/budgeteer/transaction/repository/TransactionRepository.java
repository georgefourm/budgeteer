package com.georgesdoe.budgeteer.transaction.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepository
        extends CrudRepository<TransactionEntity, Long>, PagingAndSortingRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByAmountGreaterThan(BigDecimal value, Sort sort);

    List<TransactionEntity> findByAmountLessThan(BigDecimal value, Sort sort);
}
