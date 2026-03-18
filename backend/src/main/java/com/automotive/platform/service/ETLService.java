package com.automotive.platform.service;

import com.automotive.platform.dto.*;
import com.automotive.platform.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ETL Service: simulates Extract-Transform-Load pipeline.
 * - Extract: reads data from JSON (via DTOs)
 * - Transform: validates and maps to entities
 * - Load: persists to database
 */
@Service
public class ETLService {

    private final VehicleService vehicleService;
    private final InventoryService inventoryService;
    private final DealerService dealerService;
    private final com.automotive.platform.repository.ProductionMetricsRepository productionMetricsRepository;

    public ETLService(VehicleService vehicleService,
                     InventoryService inventoryService,
                     DealerService dealerService,
                     com.automotive.platform.repository.ProductionMetricsRepository productionMetricsRepository) {
        this.vehicleService = vehicleService;
        this.inventoryService = inventoryService;
        this.dealerService = dealerService;
        this.productionMetricsRepository = productionMetricsRepository;
    }

    /**
     * Runs the full ETL pipeline: validate, transform, and store data.
     * Validation is done by Jakarta Validation on DTOs before this method is called.
     */
    @Transactional
    public Map<String, Object> runEtl(EtlDataRequest request) {
        int vehiclesLoaded = 0;
        int inventoryLoaded = 0;
        int dealersLoaded = 0;
        int metricsLoaded = 0;

        // Transform and load vehicles first (inventory/metrics reference vehicleId)
        List<Vehicle> savedVehicles = new ArrayList<>();
        if (request.getVehicles() != null) {
            for (VehicleEtlDto dto : request.getVehicles()) {
                Vehicle vehicle = transformToVehicle(dto);
                vehicle = vehicleService.save(vehicle);
                savedVehicles.add(vehicle);
                vehiclesLoaded++;
            }
        }

        // Build vehicleId -> saved Vehicle id mapping (by index, since we create in order)
        // For ETL, we assume vehicleId in DTO refers to index (1-based) or we use saved IDs
        // Simplification: inventory and metrics reference by position (1st vehicle = id 1, etc.)
        // Or we use the actual generated IDs from saved vehicles
        long[] vehicleIds = savedVehicles.stream().mapToLong(Vehicle::getId).toArray();

        // Load inventory - vehicleId in DTO must reference existing vehicle
        if (request.getInventory() != null) {
            for (InventoryEtlDto dto : request.getInventory()) {
                Long vehicleId = resolveVehicleId(dto.getVehicleId(), vehicleIds, savedVehicles);
                if (vehicleId != null) {
                    Inventory inv = transformToInventory(dto, vehicleId);
                    inventoryService.save(inv);
                    inventoryLoaded++;
                }
            }
        }

        // Load dealers
        if (request.getDealers() != null) {
            for (DealerEtlDto dto : request.getDealers()) {
                Dealer dealer = transformToDealer(dto);
                dealerService.save(dealer);
                dealersLoaded++;
            }
        }

        // Load production metrics
        if (request.getProductionMetrics() != null) {
            for (ProductionMetricsEtlDto dto : request.getProductionMetrics()) {
                Long vehicleId = resolveVehicleId(dto.getVehicleId(), vehicleIds, savedVehicles);
                if (vehicleId != null) {
                    ProductionMetrics metrics = transformToProductionMetrics(dto, vehicleId);
                    productionMetricsRepository.save(metrics);
                    metricsLoaded++;
                }
            }
        }

        return Map.of(
                "vehiclesLoaded", vehiclesLoaded,
                "inventoryLoaded", inventoryLoaded,
                "dealersLoaded", dealersLoaded,
                "productionMetricsLoaded", metricsLoaded,
                "status", "ETL completed successfully"
        );
    }

    /**
     * Resolves vehicleId: if it's a 1-based index into our batch, map to actual ID.
     * Otherwise use as direct ID if it exists in saved vehicles.
     */
    private Long resolveVehicleId(Long dtoVehicleId, long[] vehicleIds, List<Vehicle> savedVehicles) {
        if (dtoVehicleId == null) return null;
        // If 1-based index within batch
        int index = dtoVehicleId.intValue() - 1;
        if (index >= 0 && index < vehicleIds.length) {
            return vehicleIds[index];
        }
        // Check if it matches any saved vehicle ID
        for (Vehicle v : savedVehicles) {
            if (v.getId().equals(dtoVehicleId)) return v.getId();
        }
        return dtoVehicleId; // Use as-is, may fail FK if not exists
    }

    private Vehicle transformToVehicle(VehicleEtlDto dto) {
        Vehicle v = new Vehicle();
        v.setModel(dto.getModel().trim());
        v.setPlantLocation(dto.getPlantLocation().trim());
        v.setProductionDate(dto.getProductionDate());
        return v;
    }

    private Inventory transformToInventory(InventoryEtlDto dto, Long vehicleId) {
        Inventory inv = new Inventory();
        inv.setVehicleId(vehicleId);
        inv.setStatus(dto.getStatus().trim());
        inv.setWarehouseLocation(dto.getWarehouseLocation().trim());
        return inv;
    }

    private Dealer transformToDealer(DealerEtlDto dto) {
        Dealer d = new Dealer();
        d.setName(dto.getName().trim());
        d.setRegion(dto.getRegion().trim());
        return d;
    }

    private ProductionMetrics transformToProductionMetrics(ProductionMetricsEtlDto dto, Long vehicleId) {
        ProductionMetrics m = new ProductionMetrics();
        m.setVehicleId(vehicleId);
        m.setProductionTime(dto.getProductionTime());
        m.setDefectsCount(dto.getDefectsCount());
        return m;
    }
}
