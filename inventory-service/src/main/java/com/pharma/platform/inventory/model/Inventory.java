package com.pharma.platform.inventory.model;

import jakarta.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCode;
    private Integer quantity;

    // Constructors
    public Inventory() {}

    public Inventory(String productCode, Integer quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getProductCode() { return productCode; }

    public void setProductCode(String productCode) { this.productCode = productCode; }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
