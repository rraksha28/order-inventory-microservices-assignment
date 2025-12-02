package com.pharma.platform.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharma.platform.order.model.Order;
import com.pharma.platform.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void shouldReturn200WhenOrderPlaced() throws Exception {
        Order mockOrder = Order.builder().status("PLACED").build();

        Mockito.when(orderService.placeOrder(Mockito.any())).thenReturn(mockOrder);

        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString("""
                        { "productId": 1, "batchCode": "B1", "quantity": 10 }
                """)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PLACED"));
    }

    @Test
    void shouldReturn400WhenOrderFails() throws Exception {
        Order failed = Order.builder().status("FAILED").build();
        Mockito.when(orderService.placeOrder(Mockito.any())).thenReturn(failed);

        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString("""
                          { "productId": 1, "batchCode": "B1", "quantity": 10 }
                        """)))
                .andExpect(status().isBadRequest());
    }

    private static String asJsonString(Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
