package com.bookloom.user.models;

import com.bookloom.shared.models.BaseEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a user in the system.
 * <p>
 * This class extends {@link BaseEntity}, inheriting its ID and common entity properties.
 * It is annotated with {@link Document} to indicate that instances of this class are
 * stored in a MongoDB collection named "users".
 * </p>
 *
 * <p>
 * The class includes fields for the user's name, username, and password, which are
 * essential for user management and authentication.
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "users")
public class User extends BaseEntity {

    /**
     * The name of the user.
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    /**
     * The user's email.
     */
    @NotNull
    @Email
    @Size(max = 256)
    private String email;

    /**
     * The user's password used for login.
     */
    @NotNull
    private String password;
}
