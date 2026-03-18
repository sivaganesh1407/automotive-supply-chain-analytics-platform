package com.automotive.platform.service;

import com.automotive.platform.model.Vehicle;
import com.automotive.platform.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for Vehicle operations - business logic and orchestration.
 */
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findByPlantLocation(String plantLocation) {
        return vehicleRepository.findByPlantLocation(plantLocation);
    }

    public List<Vehicle> findByProductionDateRange(LocalDate start, LocalDate end) {
        return vehicleRepository.findByProductionDateBetween(start, end);
    }

    public List<Vehicle> findByModel(String model) {
        return vehicleRepository.findByModel(model);
    }
}
