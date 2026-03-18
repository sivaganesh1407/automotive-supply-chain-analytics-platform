package com.automotive.platform.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for ETL batch import - simulates JSON payload structure.
 */
public class EtlDataRequest {

    @Valid
    @NotEmpty(message = "Vehicles list cannot be empty")
    private List<VehicleEtlDto> vehicles;

    @Valid
    private List<InventoryEtlDto> inventory = new ArrayList<>();

    @Valid
    private List<DealerEtlDto> dealers = new ArrayList<>();

    @Valid
    private List<ProductionMetricsEtlDto> productionMetrics = new ArrayList<>();

    public EtlDataRequest() {
    }

    public List<VehicleEtlDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleEtlDto> vehicles) {
        this.vehicles = vehicles;
    }

    public List<InventoryEtlDto> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryEtlDto> inventory) {
        this.inventory = inventory;
    }

    public List<DealerEtlDto> getDealers() {
        return dealers;
    }

    public void setDealers(List<DealerEtlDto> dealers) {
        this.dealers = dealers;
    }

    public List<ProductionMetricsEtlDto> getProductionMetrics() {
        return productionMetrics;
    }

    public void setProductionMetrics(List<ProductionMetricsEtlDto> productionMetrics) {
        this.productionMetrics = productionMetrics;
    }
}
