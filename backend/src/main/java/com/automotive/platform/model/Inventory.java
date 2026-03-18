package com.automotive.platform.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Entity representing inventory status of a vehicle in the supply chain.
 * Status: IN_STOCK, IN_TRANSIT, or DELIVERED
 */
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Vehicle ID is required")
    @Positive(message = "Vehicle ID must be positive")
    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @NotBlank(message = "Status is required")
    @Column(nullable = false)
    private String status; // IN_STOCK, IN_TRANSIT, DELIVERED

    @NotBlank(message = "Warehouse location is required")
    @Column(name = "warehouse_location", nullable = false)
    private String warehouseLocation;

    public Inventory() {
    }

    public Inventory(Long vehicleId, String status, String warehouseLocation) {
        this.vehicleId = vehicleId;
        this.status = status;
        this.warehouseLocation = warehouseLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }
}
