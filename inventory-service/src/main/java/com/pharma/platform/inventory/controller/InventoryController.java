package com.pharma.platform.inventory.controller;

import com.pharma.platform.inventory.dto.InventoryUpdateRequest;
import com.pharma.platform.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // Called by Order Service
    @PostMapping("/update")
    public ResponseEntity<String> updateInventory(@RequestBody InventoryUpdateRequest request) {
        boolean success = service.reduceStock(request.getProductCode(), request.getQuantity());

        if (!success) {
            return ResponseEntity.badRequest().body("Insufficient stock");
        }

        return ResponseEntity.ok("Inventory updated");
    }

    // To manually add stock
    @PostMapping("/add")
    public ResponseEntity<String> addStock(@RequestBody InventoryUpdateRequest request) {
        service.addStock(request.getProductCode(), request.getQuantity());
        return ResponseEntity.ok("Stock added successfully");
    }
}
