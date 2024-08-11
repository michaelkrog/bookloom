package com.bookloom.user.models;

import com.bookloom.shared.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.convert.ValueConverter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.ExplicitEncrypted;

import java.util.List;
import java.util.Set;

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
    @Indexed(unique = true)
    private String email;

    /**
     * The user's password used for login.
     */
    @NotNull
    private String password;

    private Set<UserRole> roles = Set.of(UserRole.User);

    /**
     * Overrides getter to let Jackson ignore it when serializing it.
     * @return The encrypted password.
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }

}
