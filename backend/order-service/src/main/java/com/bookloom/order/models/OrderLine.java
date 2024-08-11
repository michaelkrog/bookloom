package com.bookloom.order.models;

import lombok.Data;

@Data
public class OrderLine {
    private String productReference;
    private String description;
    private long price;
    private int quantity;
}
