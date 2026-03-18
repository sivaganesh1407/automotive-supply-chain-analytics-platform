package com.automotive.platform.service;

import com.automotive.platform.model.Inventory;
import com.automotive.platform.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for Inventory operations - business logic and orchestration.
 */
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> findByVehicleId(Long vehicleId) {
        return inventoryRepository.findByVehicleId(vehicleId);
    }

    public List<Inventory> findByStatus(String status) {
        return inventoryRepository.findByStatus(status);
    }

    public List<Inventory> findByWarehouseLocation(String warehouseLocation) {
        return inventoryRepository.findByWarehouseLocation(warehouseLocation);
    }
}
