package com.georgesdoe.budgeteer.transaction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository
        extends CrudRepository<TransactionEntity, Long>, PagingAndSortingRepository<TransactionEntity, Long> {
}
