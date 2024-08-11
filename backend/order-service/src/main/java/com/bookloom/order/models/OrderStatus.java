package com.bookloom.order.models;

/**
 * Represents the various statuses an order can have in the system.
 * <p>
 * This enumeration defines the lifecycle stages of an order, from its creation to its completion.
 * </p>
 */
public enum OrderStatus {
    /**
     * Indicates that the order has been created but not yet processed.
     */
    Pending,

    /**
     * Indicates that the order is currently being processed.
     */
    Processing,

    /**
     * Indicates that the order has been shipped to the customer.
     */
    Shipped,

    /**
     * Indicates that the order has been delivered to the customer.
     */
    Delivered
}
