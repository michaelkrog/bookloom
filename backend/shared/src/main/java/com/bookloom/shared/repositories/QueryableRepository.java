package com.bookloom.shared.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

public interface QueryableRepository<T> {
    Page<T> findAll(Pageable pageable, Query query);
}
