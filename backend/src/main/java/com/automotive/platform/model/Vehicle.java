package com.automotive.platform.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * Entity representing a vehicle produced in the manufacturing pipeline.
 */
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Model is required")
    @Column(nullable = false)
    private String model;

    @NotBlank(message = "Plant location is required")
    @Column(name = "plant_location", nullable = false)
    private String plantLocation;

    @NotNull(message = "Production date is required")
    @Column(name = "production_date", nullable = false)
    private LocalDate productionDate;

    public Vehicle() {
    }

    public Vehicle(String model, String plantLocation, LocalDate productionDate) {
        this.model = model;
        this.plantLocation = plantLocation;
        this.productionDate = productionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
