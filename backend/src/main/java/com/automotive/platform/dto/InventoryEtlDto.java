package com.automotive.platform.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Pattern;

/**
 * DTO for Inventory data in ETL JSON payload.
 */
public class InventoryEtlDto {

    @NotNull(message = "Vehicle ID is required")
    @Positive(message = "Vehicle ID must be positive")
    private Long vehicleId;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "IN_STOCK|IN_TRANSIT|DELIVERED", message = "Status must be IN_STOCK, IN_TRANSIT, or DELIVERED")
    private String status;

    @NotBlank(message = "Warehouse location is required")
    private String warehouseLocation;

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
