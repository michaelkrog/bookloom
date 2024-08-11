package com.bookloom.book.services;

import com.bookloom.book.models.Book;
import com.bookloom.shared.repositories.Repository;
import com.bookloom.shared.services.BaseService;
import org.springframework.stereotype.Service;

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
public class BookService extends BaseService<Book> {

    /**
     * Constructs a new {@code UserService} with the specified {@link Repository}.
     *
     * @param repository the {@link Repository} to use for handling user-related operations.
     */
    public BookService(Repository<Book> repository) {
        super(repository);
    }
}
