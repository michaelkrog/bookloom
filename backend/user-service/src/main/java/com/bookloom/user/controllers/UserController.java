package com.bookloom.user.controllers;

import com.bookloom.shared.controllers.BaseController;
import com.bookloom.user.models.User;
import com.bookloom.user.services.JwtService;
import com.bookloom.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JwtService jwtService;

    /**
     * Constructs a new {@code UserController} with the specified {@link UserService}.
     *
     * @param service the {@link UserService} to use for handling user-related operations.
     */
    public UserController(UserService service) {
        super(service);
    }

    /**
     * Handles the authentication request for the current user.
     *
     * This endpoint generates a JWT token for the currently authenticated user
     * and returns it in the response. The token is used for authorizing future
     * requests from the user.
     *
     * @return ResponseEntity containing a JwtResponse with the generated JWT token.
     *         The HTTP status code is 200 OK if the token generation is successful.
     */
    @PostMapping("/actions/authenticate")
    public ResponseEntity<JwtResponse> authenticate() {
        return ResponseEntity.ok(new JwtResponse(jwtService.generateJwtTokenForCurrentUser()));
    }
}
