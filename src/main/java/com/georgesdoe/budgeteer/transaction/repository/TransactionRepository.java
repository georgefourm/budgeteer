package com.georgesdoe.budgeteer.transaction.repository;

import com.georgesdoe.budgeteer.transaction.domain.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepository
        extends CrudRepository<Transaction, Long>, PagingAndSortingRepository<Transaction, Long> {

    List<Transaction> findByAmountGreaterThan(BigDecimal value, Sort sort);

    List<Transaction> findByAmountLessThan(BigDecimal value, Sort sort);
}
