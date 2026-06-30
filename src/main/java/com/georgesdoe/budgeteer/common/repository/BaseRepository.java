package com.georgesdoe.budgeteer.common.repository;

import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends CrudRepository<T, ID> {

    /**
     * Returns the entity with the given id, or throws {@link ResourceNotFoundException}
     * referencing this repository's entity type if none exists.
     */
    T findByIdOrThrow(ID id);
}
