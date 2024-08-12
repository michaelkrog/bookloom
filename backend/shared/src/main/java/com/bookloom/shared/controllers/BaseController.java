package com.bookloom.shared.controllers;

import com.bookloom.shared.models.BaseEntity;
import com.bookloom.shared.services.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * A base controller class providing standard RESTful operations for entities that extend {@link BaseEntity}.
 * This class is generic and can be used to handle common CRUD operations for any entity type.
 *
 * The controller relies on a service layer, represented by {@link Service}, to perform business logic and data access.
 *
 * @param <T> the type of the entity, which must extend {@link BaseEntity}.
 * @param <S> the type of the service, which must extend {@link Service} for the entity type T.
 */
@RequiredArgsConstructor
public class BaseController<T extends BaseEntity, S extends Service<T>> implements EntityOperations<T> {

    protected final S service;

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the unique identifier of the entity.
     * @return a {@link ResponseEntity} containing the found entity or a no-content status if not found.
     */
    @Override
    public ResponseEntity<T> get(String id) {
        return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    /**
     * Retrieves a list of all entities with pagination support.
     *
     * @param pageable the pagination information, including page number, size, and sorting details.
     * @return a {@link ResponseEntity} containing a list of entities.
     */
    @Override
    public ResponseEntity<List<T>> list(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable).getContent());
    }

    /**
     * Creates a new entity.
     *
     * @param entity the entity to create.
     * @return a {@link ResponseEntity} containing the created entity.
     */
    @Override
    public ResponseEntity<T> create(T entity) {
        return ResponseEntity.ok(service.save(entity));
    }

    /**
     * Updates an existing entity.
     *
     * @param entity the entity with updated information.
     * @param id     the unique identifier of the entity to update.
     * @return a {@link ResponseEntity} containing the updated entity.
     */
    @Override
    public ResponseEntity<T> update(T entity, String id) {
        // Ensure the ID of the entity matches the path ID.
        entity.setId(id);
        return ResponseEntity.ok(service.save(entity));
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id the unique identifier of the entity to delete.
     */
    @Override
    public void delete(String id) {
        service.findById(id).ifPresent(service::delete);
    }
}
