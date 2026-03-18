package com.automotive.platform.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * DTO for ProductionMetrics data in ETL JSON payload.
 */
public class ProductionMetricsEtlDto {

    @NotNull(message = "Vehicle ID is required")
    @Positive(message = "Vehicle ID must be positive")
    private Long vehicleId;

    @NotNull(message = "Production time is required")
    @PositiveOrZero(message = "Production time must be zero or positive")
    private Double productionTime;

    @NotNull(message = "Defects count is required")
    @PositiveOrZero(message = "Defects count must be zero or positive")
    private Integer defectsCount;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Double getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Double productionTime) {
        this.productionTime = productionTime;
    }

    public Integer getDefectsCount() {
        return defectsCount;
    }

    public void setDefectsCount(Integer defectsCount) {
        this.defectsCount = defectsCount;
    }
}
