package com.pharma.platform.order.repository;

import com.pharma.platform.order.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldSaveOrder() {
        Order order = Order.builder()
                .productId(1L)
                .batchCode("B001")
                .quantity(5)
                .status("PLACED")
                .build();

        Order saved = orderRepository.save(order);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getStatus()).isEqualTo("PLACED");
    }
}
