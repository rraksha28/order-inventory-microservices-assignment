package com.pharma.platform.inventory.service;

import com.pharma.platform.inventory.model.Inventory;
import com.pharma.platform.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public boolean reduceStock(String productCode, int qty) {
        Inventory inventory = repository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (inventory.getQuantity() < qty) {
            return false;
        }

        inventory.setQuantity(inventory.getQuantity() - qty);
        repository.save(inventory);
        return true;
    }

    public void addStock(String productCode, int qty) {
        Inventory inv = new Inventory(productCode, qty);
        repository.save(inv);
    }
}
