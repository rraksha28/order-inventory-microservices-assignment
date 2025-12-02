package com.pharma.platform.order.service;

import com.pharma.platform.order.client.InventoryClient;
import com.pharma.platform.order.dto.OrderRequest;
import com.pharma.platform.order.model.Order;
import com.pharma.platform.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private InventoryClient inventoryClient;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        inventoryClient = mock(InventoryClient.class);
        orderService = new OrderService(orderRepository, inventoryClient);
    }

    @Test
    void shouldPlaceOrderSuccessfully() {

        OrderRequest request = new OrderRequest();
        request.setProductId(1L);
        request.setBatchCode("B001");
        request.setQuantity(10);

        when(inventoryClient.updateInventory(any())).thenReturn(true);
        when(orderRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Order result = orderService.placeOrder(request);

        assertEquals("PLACED", result.getStatus());
        verify(orderRepository).save(any());
    }

    @Test
    void shouldFailOrderWhenInventoryFails() {

        when(inventoryClient.updateInventory(any())).thenReturn(false);

        OrderRequest request = new OrderRequest();
        request.setProductId(1L);
        request.setBatchCode("B001");
        request.setQuantity(10);

        Order result = orderService.placeOrder(request);

        assertEquals("FAILED", result.getStatus());
    }
}
