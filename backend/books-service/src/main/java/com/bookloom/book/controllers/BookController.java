package com.bookloom.book.controllers;

import com.bookloom.book.models.Book;
import com.bookloom.book.services.BookService;
import com.bookloom.shared.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link Book} entities.
 *
 * <p>
 * This controller extends {@link BaseController} to provide standard CRUD operations
 * for the {@link Book} entity. It leverages the {@link BookService} for business logic
 * and data access.
 * </p>
 *
 * <p>
 * The controller is mapped to handle HTTP requests to the "/users" endpoint.
 * </p>
 *
 * @see BaseController
 * @see BookService
 */
@RestController
@RequestMapping("/books")
public class BookController extends BaseController<Book, BookService> {

    /**
     * Constructs a new {@code UserController} with the specified {@link BookService}.
     *
     * @param service the {@link BookService} to use for handling user-related operations.
     */
    public BookController(BookService service) {
        super(service);
    }
}
