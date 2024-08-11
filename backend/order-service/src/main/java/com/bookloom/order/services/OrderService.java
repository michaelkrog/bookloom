package com.bookloom.order.services;

import com.bookloom.order.models.Order;
import com.bookloom.shared.repositories.Repository;
import com.bookloom.shared.services.BaseService;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link Order} entities.
 * <p>
 * This class extends {@link BaseService} to provide business logic and data access
 * operations specific to {@link Order} entities. It is annotated with {@link Service}
 * to indicate that it is a Spring service component.
 * </p>
 *
 * <p>
 * The service leverages the generic repository provided by {@link BaseService} to
 * perform CRUD operations on {@link Order} entities.
 * </p>
 *
 * @see BaseService
 * @see Repository
 */
@Service
public class OrderService extends BaseService<Order> {

    /**
     * Constructs a new {@code UserService} with the specified {@link Repository}.
     *
     * @param repository the {@link Repository} to use for handling user-related operations.
     */
    public OrderService(Repository<Order> repository) {
        super(repository);
    }
}
