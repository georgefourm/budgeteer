package com.georgesdoe.budgeteer.category.repository;

import com.georgesdoe.budgeteer.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends BaseRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findFirstByNameIgnoreCase(String name);

    /**
     * Resolves the category of the highest-importance rule whose regex matches the given text,
     * using Postgres' POSIX regex operator ({@code ~}).
     */
    @Query(value = """
            SELECT c.*
            FROM categories c
            JOIN category_rules r ON r.category_id = c.id
            WHERE :text ~ r.rule_regex
            ORDER BY r.importance DESC NULLS LAST, r.id ASC
            LIMIT 1
            """, nativeQuery = true)
    Optional<CategoryEntity> findFirstMatchingRule(@Param("text") String text);
}
