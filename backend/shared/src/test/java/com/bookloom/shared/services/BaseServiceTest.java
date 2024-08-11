package com.bookloom.shared.services;

import com.bookloom.shared.models.TestEntity;
import com.bookloom.shared.repositories.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseServiceTest {

    
    @Mock
    private Repository<TestEntity> repository;

    @InjectMocks
    private BaseService<TestEntity> baseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        // Arrange
        var entity = new TestEntity();
        String id = "1";
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        // Act
        var result = baseService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(entity, result.get());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        String id = "1";
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act
        var result = baseService.findById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testFindAll() {
        // Arrange
        var pageable = Pageable.unpaged();
        TestEntity entity = new TestEntity();
        var page = new PageImpl<>(Collections.singletonList(entity));
        when(repository.findAll(pageable)).thenReturn(page);

        var result = baseService.findAll(pageable);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertTrue(result.getContent().contains(entity));
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void testSave() {
        TestEntity entity = new TestEntity();
        when(repository.save(entity)).thenReturn(entity);

        TestEntity result = baseService.save(entity);
        assertNotNull(result);
        assertEquals(entity, result);
        verify(repository, times(1)).save(entity);
    }

    @Test
    void testDelete() {
        TestEntity entity = new TestEntity();
        doNothing().when(repository).delete(entity);

        baseService.delete(entity);

        verify(repository, times(1)).delete(entity);
    }
}