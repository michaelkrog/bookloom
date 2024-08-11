package com.bookloom.book.repositories;

import com.bookloom.book.models.Book;
import com.bookloom.shared.repositories.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing {@link Book} entities.
 *
 * <p>
 * This interface extends both {@link Repository} and {@link MongoRepository} to provide
 * comprehensive data access operations for {@link Book} entities. It combines the functionalities
 * of a generic repository with those of a MongoDB-specific repository, enabling CRUD operations
 * and custom queries on the "books" collection in MongoDB.
 * </p>
 *
 * @see Repository
 * @see MongoRepository
 */
public interface BookRepository extends Repository<Book>, MongoRepository<Book, String> {
}
