package com.bookloom.shared.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Describes an abstract class for persistable entities in the system.
 * Any class that needs to support persistence, should inherit from this class.
 */
@Data
public abstract class BaseEntity implements Entity {
    /**
     * The id of the entity.
     */
    @Id
    private String id;

    /**
     * Whether the entity is a new instance or has already been persisted.
     * Based on whether the entity has an id or not.
     * @return Whether the entity is new or not.
     */
    @Override
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }
}
