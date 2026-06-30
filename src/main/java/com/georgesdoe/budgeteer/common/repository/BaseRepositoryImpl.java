package com.georgesdoe.budgeteer.common.repository;

import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * Default base implementation for all repositories, wired in via
 * {@code @EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)}.
 * Adds {@link BaseRepository#findByIdOrThrow(Object)} on top of the standard CRUD behaviour.
 */
public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final Class<T> entityClass;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityClass = entityInformation.getJavaType();
    }

    @Override
    public T findByIdOrThrow(ID id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException(entityClass));
    }
}
