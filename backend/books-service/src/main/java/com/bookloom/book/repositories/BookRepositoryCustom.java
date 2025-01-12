package com.bookloom.book.repositories;

import com.bookloom.book.models.Book;
import com.bookloom.book.models.Category;
import com.bookloom.shared.repositories.QueryableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface BookRepositoryCustom extends QueryableRepository<Book> {

    Page<Book> findAll(Collection<String> authors, Collection<Category> categories, int minPrice, int maxPrice, Pageable pageable);
}
