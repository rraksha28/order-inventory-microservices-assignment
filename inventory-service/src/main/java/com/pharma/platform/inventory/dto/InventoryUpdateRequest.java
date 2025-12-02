package com.pharma.platform.inventory.dto;

public class InventoryUpdateRequest {

    private String productCode;
    private int quantity;

    public InventoryUpdateRequest() {}

    public InventoryUpdateRequest(String productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public String getProductCode() { return productCode; }

    public void setProductCode(String productCode) { this.productCode = productCode; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
