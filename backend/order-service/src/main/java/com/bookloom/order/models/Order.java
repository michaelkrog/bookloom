package com.bookloom.order.models;

import com.bookloom.shared.models.BaseEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order within the system.
 * <p>
 * This class extends {@link BaseEntity} and includes additional fields specific to an order.
 * </p>
 *
 * @see BaseEntity
 * @see OrderLine
 * @see OrderStatus
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document("orders")
public class Order extends BaseEntity {

    /**
     * The unique identifier of the user who placed the order.
     * <p>
     * This field stores the ID of the user associated with the order.
     * </p>
     */
    private String userId;

    /**
     * The name associated with the order.
     * <p>
     * This field must be non-null and its length must be between 1 and 100 characters.
     * </p>
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    /**
     * The address where the order should be delivered.
     * <p>
     * This field must be non-null and its length must be between 1 and 100 characters.
     * </p>
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String address;

    /**
     * The postal code for the delivery address.
     * <p>
     * This field must be non-null and its length must be between 1 and 15 characters.
     * </p>
     */
    @NotNull
    @Size(min = 1, max = 15)
    private String postalCode;

    /**
     * The city for the delivery address.
     * <p>
     * This field must be non-null and its length must be between 1 and 100 characters.
     * </p>
     */
    @NotNull
    @Size(min = 1, max = 100)
    private String city;

    /**
     * The country code for the delivery address.
     * <p>
     * This field must be non-null and its length must be exactly 2 characters.
     * </p>
     */
    @NotNull
    @Size(min = 2, max = 2)
    private String countryCode;

    /**
     * A list of order lines associated with this order.
     * <p>
     * This field stores the individual items included in the order.
     * The list is initialized to an empty list by default.
     * </p>
     */
    private List<OrderLine> orderLines = new ArrayList<>();

    /**
     * The current status of the order.
     * <p>
     * This field represents the status of the order within the system, such as "pending", "shipped", etc.
     * </p>
     */
    private OrderStatus status = OrderStatus.Pending;
}
