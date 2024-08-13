package com.bookloom.book.repositories;

import com.bookloom.book.models.Book;
import com.bookloom.book.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Criteria.*;

import java.util.Collection;

public class BookRepositoryImpl implements BookRepositoryCustom {

    @Autowired
    private MongoTemplate template;


    @Override
    public Page<Book> findAll(Collection<String> authors, Collection<Category> categories, int minPrice, int maxPrice, Pageable pageable) {
        var query = new Query(where("price").gte(minPrice).lt(maxPrice));

        if(authors != null && !authors.isEmpty()) {
            query.addCriteria(where("author").in(authors));
        }

        if(categories != null && !categories.isEmpty()) {
            query.addCriteria(where("category").in(categories));
        }

        var count = template.count(query, Book.class);
        var result = template.find(query.with(pageable), Book.class);

        return new PageImpl<>(result, pageable, count);
    }
}
