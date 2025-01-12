package com.bookloom.shared.repositories;

import com.bookloom.shared.models.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.parameters.P;

import java.lang.reflect.ParameterizedType;

public abstract class BaseRepositoryImpl<T extends BaseEntity> implements QueryableRepository<T> {
    @Autowired
    MongoTemplate template;

    private final Class<T> entityClass;
    
    @SuppressWarnings("unchecked")
    public BaseRepositoryImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public Page<T> findAll(Pageable pageable, Query query) {
        long count = template.count(query, entityClass);
        var entities = template.find(query.with(pageable), entityClass);

        return new PageImpl<>(entities, pageable, count);
    }
}
