package com.automotive.platform.repository;

import com.automotive.platform.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository for Inventory entity - handles database operations.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByVehicleId(Long vehicleId);

    List<Inventory> findByStatus(String status);

    List<Inventory> findByWarehouseLocation(String warehouseLocation);
}
