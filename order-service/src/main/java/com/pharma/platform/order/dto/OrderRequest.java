package com.pharma.platform.order.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long productId;
    private String batchCode;
    private int quantity;
}
