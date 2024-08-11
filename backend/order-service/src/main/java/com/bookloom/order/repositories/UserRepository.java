package com.bookloom.order.repositories;

import com.bookloom.shared.repositories.Repository;
import com.bookloom.user.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing {@link OrderRepository} entities.
 *
 * <p>
 * This interface extends both {@link Repository} and {@link MongoRepository} to provide
 * comprehensive data access operations for {@link OrderRepository} entities. It combines the functionalities
 * of a generic repository with those of a MongoDB-specific repository, enabling CRUD operations
 * and custom queries on the "orders" collection in MongoDB.
 * </p>
 *
 * @see Repository
 * @see MongoRepository
 */
public interface OrderRepository extends Repository<OrderRepository>, MongoRepository<OrderRepository, String> {
}
