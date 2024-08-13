package com.bookloom.book.controllers;

import com.bookloom.book.models.Book;
import com.bookloom.book.models.Category;
import com.bookloom.book.services.BookService;
import com.bookloom.shared.controllers.BaseController;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
 * The controller is mapped to handle HTTP requests to the "/books" endpoint.
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

    @GetMapping(path = "", params = "filtered=true")
    public ResponseEntity<List<Book>> list(Pageable pageable, @RequestParam(required = false) String price,
                                           @RequestParam(required = false) List<String> author, @RequestParam(required = false) List<Category> category) {
        int minPrice = 0;
        int maxPrice = Integer.MAX_VALUE;
        if("<10".equals(price)) {
            maxPrice = 1000;
        } else if("10-20".equals(price)) {
            minPrice = 1000;
            maxPrice = 2000;
        } else if("20-40".equals(price)) {
            minPrice = 2000;
            maxPrice = 4000;
        } else if("40>".equals(price)) {
            minPrice = 4000;
        }

        return ResponseEntity.ok(service.findAll(author, category, minPrice, maxPrice, pageable).getContent());
    }
}
