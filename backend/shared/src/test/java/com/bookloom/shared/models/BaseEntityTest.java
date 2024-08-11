package com.bookloom.shared.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    @Test
    void testIsNewWhenIdIsNull() {
        TestEntity entity = new TestEntity();
        entity.setId(null);  // Ensure the ID is null

        assertTrue(entity.isNew(), "Entity should be considered new if ID is null");
    }

    @Test
    void testIsNewWhenIdIsNotNull() {
        TestEntity entity = new TestEntity();
        entity.setId("12345");  // Set a non-null ID

        assertFalse(entity.isNew(), "Entity should not be considered new if ID is not null");
    }
}
