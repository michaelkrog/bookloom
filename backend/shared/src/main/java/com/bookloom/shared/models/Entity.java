package com.bookloom.shared.models;

import org.springframework.data.domain.Persistable;

/**
 * A marker interface for entities, indicating that the implementing class represents a
 * persistable entity with a {@link String} identifier.
 *
 * This interface extends {@link Persistable} to indicate that the entity has a persistent identity.
 *
 */
public interface Entity extends Persistable<String> {

}
