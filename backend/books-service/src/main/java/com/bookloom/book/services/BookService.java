package com.bookloom.book.services;

import com.bookloom.book.models.Book;
import com.bookloom.book.models.Category;
import com.bookloom.book.repositories.BookRepository;
import com.bookloom.shared.repositories.Repository;
import com.bookloom.shared.services.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service class for managing {@link Book} entities.
 * <p>
 * This class extends {@link BaseService} to provide business logic and data access
 * operations specific to {@link com.bookloom.book.models.Book} entities. It is annotated with {@link Service}
 * to indicate that it is a Spring service component.
 * </p>
 *
 * <p>
 * The service leverages the generic repository provided by {@link BaseService} to
 * perform CRUD operations on {@link Book} entities.
 * </p>
 *
 * @see BaseService
 * @see Repository
 */
@Service
public class BookService extends BaseService<Book, BookRepository> {

    /**
     * Constructs a new {@code BookService} with the specified {@link Repository}.
     *
     * @param repository the {@link Repository} to use for handling user-related operations.
     */
    public BookService(BookRepository repository) {
        super(repository);
    }


    public Page<Book> findAll(Collection<String> authors, Collection<Category> categories, int minPrice, int maxPrice, Pageable pageable) {
        return repository.findAll(authors, categories, minPrice, maxPrice, pageable);
    }
}
