package com.pharma.platform.order.dto;

import lombok.Data;

@Data
public class InventoryUpdateRequest {
    private Long productId;
    private String batchCode;
    private int quantity;
}
