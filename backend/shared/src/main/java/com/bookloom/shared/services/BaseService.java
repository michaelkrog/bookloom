package com.bookloom.shared.services;

import com.bookloom.shared.models.BaseEntity;
import com.bookloom.shared.repositories.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;

import java.util.Optional;

/**
 * A base service implementation of the {@link Service} interface, providing
 * common CRUD operations for entities that extend {@link BaseEntity}.
 * This class uses a {@link Repository} for data access and enforces security
 * restrictions using Spring Security's {@link Secured} annotations.
 *
 * @param <T> the type of the entity, which must extend from {@link BaseEntity}.
 */
@RequiredArgsConstructor
public class BaseService<T extends BaseEntity, R extends Repository<T>> implements Service<T> {

    protected final R repository;

    /**
     * Finds an entity by its ID, secured to allow only users with the "ROLE_USER" authority.
     *
     * @param id the unique identifier of the entity.
     * @return an {@link Optional} containing the found entity, or {@link Optional#empty()}
     *         if no entity is found with the given ID.
     */
    @Override
    @Secured({ "ROLE_USER" })
    public Optional<T> findById(String id) {
        return repository.findById(id);
    }

    /**
     * Retrieves all entities with pagination, secured to allow only users with the "ROLE_USER" authority.
     *
     * @param pageable the pagination information, including page number, size, and sorting details.
     * @return a {@link Page} containing a list of entities that match the pagination criteria.
     */
    @Override
    @Secured({ "ROLE_USER" })
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Saves a given entity, secured to allow only users with the "ROLE_USER" authority.
     * If the entity does not exist, it will be created; if it exists, it will be updated.
     *
     * @param entity the entity to save.
     * @return the saved entity.
     */
    @Override
    @Secured({ "ROLE_USER" })
    public T save(T entity) {
        return repository.save(entity);
    }

    /**
     * Deletes a given entity, secured to allow only users with the "ROLE_USER" authority.
     *
     * @param entity the entity to delete.
     */
    @Override
    @Secured({ "ROLE_USER" })
    public void delete(T entity) {
        repository.delete(entity);
    }
}
