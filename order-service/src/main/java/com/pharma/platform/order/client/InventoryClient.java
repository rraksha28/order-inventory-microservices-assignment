package com.pharma.platform.order.client;

import com.pharma.platform.order.dto.InventoryUpdateRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {

    private static final String INVENTORY_BASE_URL = "http://localhost:8081/inventory";

    private final RestTemplate restTemplate;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean updateInventory(InventoryUpdateRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<InventoryUpdateRequest> entity =
                new HttpEntity<>(request, headers);

        try {
            ResponseEntity<String> response =
                    restTemplate.postForEntity(INVENTORY_BASE_URL + "/update", entity, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
