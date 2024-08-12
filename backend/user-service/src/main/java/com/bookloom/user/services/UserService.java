package com.bookloom.user.services;

import com.bookloom.shared.repositories.Repository;
import com.bookloom.shared.services.BaseService;
import com.bookloom.user.models.User;
import com.bookloom.user.repositories.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Service class for managing {@link User} entities.
 * <p>
 * This class extends {@link BaseService} to provide business logic and data access
 * operations specific to {@link User} entities. It is annotated with {@link Service}
 * to indicate that it is a Spring service component.
 * </p>
 *
 * <p>
 * The service leverages the generic repository provided by {@link BaseService} to
 * perform CRUD operations on {@link User} entities.
 * </p>
 *
 * @see BaseService
 * @see Repository
 */
@Service
public class UserService extends BaseService<User, UserRepository> {


    /**
     * Constructs a new {@code UserService} with the specified {@link Repository}.
     *
     * @param repository the {@link Repository} to use for handling user-related operations.
     */
    public UserService(UserRepository repository) {
        super(repository);
    }

}
