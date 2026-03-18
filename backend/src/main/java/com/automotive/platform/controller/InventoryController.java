package com.automotive.platform.controller;

import com.automotive.platform.model.Inventory;
import com.automotive.platform.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Inventory endpoints.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * GET /inventory - returns all inventory records.
     */
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventory = inventoryService.findAll();
        return ResponseEntity.ok(inventory);
    }

    /**
     * GET /inventory/{id} - returns a single inventory record by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        return inventoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /inventory/status/{status} - returns inventory by status (IN_STOCK, IN_TRANSIT, DELIVERED).
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Inventory>> getInventoryByStatus(@PathVariable String status) {
        List<Inventory> inventory = inventoryService.findByStatus(status);
        return ResponseEntity.ok(inventory);
    }
}
