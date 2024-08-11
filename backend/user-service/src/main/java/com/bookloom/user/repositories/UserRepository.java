package com.bookloom.user.repositories;

import com.bookloom.shared.repositories.Repository;
import com.bookloom.user.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing {@link User} entities.
 *
 * <p>
 * This interface extends both {@link Repository} and {@link MongoRepository} to provide
 * comprehensive data access operations for {@link User} entities. It combines the functionalities
 * of a generic repository with those of a MongoDB-specific repository, enabling CRUD operations
 * and custom queries on the "users" collection in MongoDB.
 * </p>
 *
 * @see Repository
 * @see MongoRepository
 */
public interface UserRepository extends Repository<User>, MongoRepository<User, String> {
}
