package com.automotive.platform.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * DTO for Vehicle data in ETL JSON payload.
 */
public class VehicleEtlDto {

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Plant location is required")
    private String plantLocation;

    @NotNull(message = "Production date is required")
    private LocalDate productionDate;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlantLocation() {
        return plantLocation;
    }

    public void setPlantLocation(String plantLocation) {
        this.plantLocation = plantLocation;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }
}
