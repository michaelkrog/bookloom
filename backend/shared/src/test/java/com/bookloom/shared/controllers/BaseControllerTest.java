package com.bookloom.shared.controllers;

import com.bookloom.shared.models.BaseEntity;
import com.bookloom.shared.models.TestEntity;
import com.bookloom.shared.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseControllerTest {

    @Mock
    private Service<BaseEntity> service;

    @InjectMocks
    private BaseController<BaseEntity, Service<BaseEntity>> baseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEntityFound() {
        var entity = new TestEntity();
        String id = "1";
        when(service.findById(id)).thenReturn(Optional.of(entity));

        ResponseEntity<BaseEntity> response = baseController.get(id);

        assertEquals(ResponseEntity.ok(entity), response);
        verify(service, times(1)).findById(id);
    }

    @Test
    void testGetEntityNotFound() {
        String id = "1";
        when(service.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<BaseEntity> response = baseController.get(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(service, times(1)).findById(id);
    }

    @Test
    void testListEntities() {
        var entity = new TestEntity();
        Pageable pageable = Pageable.unpaged();
        Page<BaseEntity> page = new PageImpl<>(Collections.singletonList(entity));
        when(service.findAll(pageable)).thenReturn(page);

        ResponseEntity<List<BaseEntity>> response = baseController.list(pageable);

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
        assertTrue(response.getBody().contains(entity));
        verify(service, times(1)).findAll(pageable);
    }

    @Test
    void testCreateEntity() {
        var entity = new TestEntity();
        when(service.save(entity)).thenReturn(entity);

        ResponseEntity<BaseEntity> response = baseController.create(entity);

        assertEquals(ResponseEntity.ok(entity), response);
        verify(service, times(1)).save(entity);
    }

    @Test
    void testUpdateEntity() {
        var entity = new TestEntity();
        String id = "1";
        entity.setId(id);
        when(service.save(entity)).thenReturn(entity);

        ResponseEntity<BaseEntity> response = baseController.update(entity, id);

        assertEquals(ResponseEntity.ok(entity), response);
        verify(service, times(1)).save(entity);
    }

    @Test
    void testDeleteEntity() {
        String id = "1";
        var entity = new TestEntity();
        when(service.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(service).delete(entity);

        baseController.delete(id);

        verify(service, times(1)).findById(id);
        verify(service, times(1)).delete(entity);
    }
}
