package com.pharma.platform.order.service;

import com.pharma.platform.order.client.InventoryClient;
import com.pharma.platform.order.dto.InventoryUpdateRequest;
import com.pharma.platform.order.dto.OrderRequest;
import com.pharma.platform.order.model.Order;
import com.pharma.platform.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public Order placeOrder(OrderRequest request) {

        InventoryUpdateRequest inventoryRequest = new InventoryUpdateRequest();
        inventoryRequest.setProductId(request.getProductId());
        inventoryRequest.setBatchCode(request.getBatchCode());
        inventoryRequest.setQuantity(request.getQuantity());

        boolean updated = inventoryClient.updateInventory(inventoryRequest);

        Order order = Order.builder()
                .productId(request.getProductId())
                .batchCode(request.getBatchCode())
                .quantity(request.getQuantity())
                .status(updated ? "PLACED" : "FAILED")
                .build();

        return orderRepository.save(order);
    }
}
