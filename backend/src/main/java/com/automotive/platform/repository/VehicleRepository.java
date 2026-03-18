package com.automotive.platform.repository;

import com.automotive.platform.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * JPA Repository for Vehicle entity - handles database operations.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByPlantLocation(String plantLocation);

    List<Vehicle> findByProductionDateBetween(LocalDate start, LocalDate end);

    List<Vehicle> findByModel(String model);
}
