package com.automotive.platform.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * Entity representing production metrics for a vehicle (time, defects).
 */
@Entity
@Table(name = "production_metrics")
public class ProductionMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Vehicle ID is required")
    @Positive(message = "Vehicle ID must be positive")
    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @NotNull(message = "Production time is required")
    @PositiveOrZero(message = "Production time must be zero or positive")
    @Column(name = "production_time", nullable = false)
    private Double productionTime;

    @NotNull(message = "Defects count is required")
    @PositiveOrZero(message = "Defects count must be zero or positive")
    @Column(name = "defects_count", nullable = false)
    private Integer defectsCount;

    public ProductionMetrics() {
    }

    public ProductionMetrics(Long vehicleId, Double productionTime, Integer defectsCount) {
        this.vehicleId = vehicleId;
        this.productionTime = productionTime;
        this.defectsCount = defectsCount;
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
