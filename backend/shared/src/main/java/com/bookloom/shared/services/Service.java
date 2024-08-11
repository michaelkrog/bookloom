package com.bookloom.shared.services;

import com.bookloom.shared.models.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * A generic service interface for managing entities that extend from {@link BaseEntity}.
 * This interface provides common CRUD (Create, Read, Update, Delete) operations for any
 * entity type that extends from {@link BaseEntity}.
 *
 * @param <T> the type of the entity, which must extend from {@link BaseEntity}.
 */
public interface Service<T extends BaseEntity> {

    /**
     * Finds an entity by its ID.
     *
     * @param id the unique identifier of the entity.
     * @return an {@link Optional} containing the found entity, or {@link Optional#empty()}
     *         if no entity is found with the given ID.
     */
    public Optional<T> findById(String id);

    /**
     * Retrieves all entities, with pagination support.
     *
     * @param pageable the pagination information, including page number, size, and sorting details.
     * @return a {@link Page} containing a list of entities that match the pagination criteria.
     */
    public Page<T> findAll(Pageable pageable);

    /**
     * Saves a given entity.
     * If the entity does not exist, it will be created; if it exists, it will be updated.
     *
     * @param entity the entity to save.
     * @return the saved entity.
     */
    public T save(T entity);

    /**
     * Deletes a given entity.
     *
     * @param entity the entity to delete.
     */
    public void delete(T entity);
}

