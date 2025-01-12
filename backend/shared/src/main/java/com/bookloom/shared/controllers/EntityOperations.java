package com.bookloom.shared.controllers;

import com.bookloom.shared.controllers.bind.annotation.RequestFilter;
import com.bookloom.shared.models.BaseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface EntityOperations<T extends BaseEntity> {

    @GetMapping("")
    public ResponseEntity<List<T>> list(Pageable pageable, @RequestFilter Query query);

    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable String id);

    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody T entity);

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@RequestBody T entity, @PathVariable String id);

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id);
}
