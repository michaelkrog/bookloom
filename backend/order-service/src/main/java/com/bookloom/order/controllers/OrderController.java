package com.bookloom.order.controllers;

import com.bookloom.order.models.Order;
import com.bookloom.order.services.OrderService;
import com.bookloom.shared.controllers.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link User} entities.
 *
 * <p>
 * This controller extends {@link BaseController} to provide standard CRUD operations
 * for the {@link User} entity. It leverages the {@link UserService} for business logic
 * and data access.
 * </p>
 *
 * <p>
 * The controller is mapped to handle HTTP requests to the "/users" endpoint.
 * </p>
 *
 * @see BaseController
 * @see OrderService
 */
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController<Order, OrderService> {

    /**
     * Constructs a new {@code UserController} with the specified {@link OrderService}.
     *
     * @param service the {@link OrderService} to use for handling user-related operations.
     */
    public OrderController(OrderService service) {
        super(service);
    }
}
