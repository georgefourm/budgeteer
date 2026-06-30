package com.georgesdoe.budgeteer.transaction.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository
        extends CrudRepository<TransactionEntity, Long>, PagingAndSortingRepository<TransactionEntity, Long> {

    /**
     * Assigns the given category to every transaction whose description matches the supplied
     * regex, using Postgres' POSIX regex operator ({@code ~}). Returns the number of rows updated.
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE transactions SET category_id = :categoryId WHERE description ~ :regex",
            nativeQuery = true)
    int assignCategoryByDescriptionMatch(@Param("categoryId") Long categoryId, @Param("regex") String regex);
}
