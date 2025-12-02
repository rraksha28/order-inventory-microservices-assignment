package com.pharma.platform.order.controller;

import com.pharma.platform.order.dto.OrderRequest;
import com.pharma.platform.order.model.Order;
import com.pharma.platform.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest request) {

        Order order = orderService.placeOrder(request);

        if ("PLACED".equals(order.getStatus())) {
            return ResponseEntity.ok(order);
        }

        return ResponseEntity.badRequest().body(order);
    }
}
