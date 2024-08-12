package com.bookloom.shared.repositories;

import com.bookloom.shared.models.BaseEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * A generic repository interface for managing entities that extend from {@link BaseEntity}.
 * This interface extends both {@link PagingAndSortingRepository} and {@link CrudRepository},
 * providing additional capabilities for pagination and sorting along with the basic CRUD operations.
 *
 * @param <T> the type of the entity, which must extend from {@link BaseEntity}.
 */
@ConditionalOnExpression("false")
public interface Repository<T extends BaseEntity> extends PagingAndSortingRepository<T, String>,
        CrudRepository<T, String>, QueryByExampleExecutor<T> { }
