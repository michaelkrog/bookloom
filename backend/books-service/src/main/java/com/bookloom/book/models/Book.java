package com.bookloom.book.models;

import com.bookloom.shared.models.BaseEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The {@code Book} class represents a book entity with various attributes such as title, author, description, price, and image data.
 * This class uses the Lombok `@Data` annotation to automatically generate boilerplate code such as getters, setters,
 * `toString()`, `equals()`, and `hashCode()` methods.
 * <p>
 * The price of the book is represented in cents to avoid issues with floating-point precision when dealing with currency.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Book book = new Book();
 * book.setTitle("Effective Java");
 * book.setAuthor("Joshua Bloch");
 * book.setDescription("A comprehensive guide to programming in Java.");
 * book.setPrice(4500); // Represents $45.00
 * book.setImageData("base64EncodedImageString");
 * }</pre>
 *
 * <p>Note: The {@code imageData} field is intended to store image data as a base64 encoded string.</p>
 *
 * @author [Your Name]
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Book extends BaseEntity {

    /**
     * The title of the book.
     */
    @NotNull
    @Size(min = 1, max = 512)
    private String title;

    /**
     * A brief description of the book. .
     */
    @Size(min = 1, max = 4096)
    private String description;

    /**
     * The author of the book.
     */
    @Size(min = 1, max = 128)
    private String author;

    /**
     * The price of the book in cents.
     * <p>
     * For example, a price of 4500 represents $45.00.
     * </p>
     */
    private long price;

    /**
     * The image in jpg-format stored in Base64-encoding.
     */
    private String imageData;

    /**
     * The category the book belongs to.
     */
    @NotNull
    private Category category = Category.Other;

}

