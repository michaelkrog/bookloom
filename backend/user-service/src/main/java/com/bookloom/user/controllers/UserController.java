package com.bookloom.user.controllers;

import com.bookloom.shared.controllers.BaseController;
import com.bookloom.user.models.User;
import com.bookloom.user.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link User} entities.
 *
 * <p>
 * This controller extends {@link com.bookloom.shared.controllers.BaseController} to provide standard CRUD operations
 * for the {@link User} entity. It leverages the {@link UserService} for business logic
 * and data access.
 * </p>
 *
 * <p>
 * The controller is mapped to handle HTTP requests to the "/users" endpoint.
 * </p>
 *
 * @see BaseController
 * @see UserService
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, UserService> {

    /**
     * Constructs a new {@code UserController} with the specified {@link UserService}.
     *
     * @param service the {@link UserService} to use for handling user-related operations.
     */
    public UserController(UserService service) {
        super(service);
    }

    @PostMapping("/actions/authenticate")
    public ResponseEntity<JwtResponse> authenticate() {
        return null;
    }
}
