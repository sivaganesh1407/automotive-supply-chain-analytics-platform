package com.automotive.platform.service;

import com.automotive.platform.model.Dealer;
import com.automotive.platform.model.Inventory;
import com.automotive.platform.model.ProductionMetrics;
import com.automotive.platform.model.Vehicle;
import com.automotive.platform.repository.DealerRepository;
import com.automotive.platform.repository.InventoryRepository;
import com.automotive.platform.repository.ProductionMetricsRepository;
import com.automotive.platform.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for analytics calculations - production efficiency, defect rates, dealer performance.
 */
@Service
public class AnalyticsService {

    private final VehicleRepository vehicleRepository;
    private final InventoryRepository inventoryRepository;
    private final DealerRepository dealerRepository;
    private final ProductionMetricsRepository productionMetricsRepository;

    public AnalyticsService(VehicleRepository vehicleRepository,
                           InventoryRepository inventoryRepository,
                           DealerRepository dealerRepository,
                           ProductionMetricsRepository productionMetricsRepository) {
        this.vehicleRepository = vehicleRepository;
        this.inventoryRepository = inventoryRepository;
        this.dealerRepository = dealerRepository;
        this.productionMetricsRepository = productionMetricsRepository;
    }

    /**
     * Production analytics: efficiency, defect rates, total vehicles.
     */
    public Map<String, Object> getProductionAnalytics() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<ProductionMetrics> metrics = productionMetricsRepository.findAll();

        double totalProductionTime = metrics.stream()
                .mapToDouble(m -> m.getProductionTime() != null ? m.getProductionTime() : 0)
                .sum();
        int totalDefects = metrics.stream()
                .mapToInt(m -> m.getDefectsCount() != null ? m.getDefectsCount() : 0)
                .sum();
        int totalVehicles = vehicles.size();

        // Production efficiency: inverse of avg time (higher = better), normalized
        double avgProductionTime = totalVehicles > 0 ? totalProductionTime / totalVehicles : 0;
        double efficiency = avgProductionTime > 0 ? 100.0 / (1 + avgProductionTime / 60) : 0;

        // Defect rate: defects per vehicle as percentage
        double defectRate = totalVehicles > 0 ? (totalDefects * 100.0) / totalVehicles : 0;

        Map<String, Object> result = new HashMap<>();
        result.put("totalVehicles", totalVehicles);
        result.put("totalProductionTime", totalProductionTime);
        result.put("averageProductionTime", avgProductionTime);
        result.put("totalDefects", totalDefects);
        result.put("defectRate", Math.round(defectRate * 100.0) / 100.0);
        result.put("efficiencyScore", Math.round(efficiency * 100.0) / 100.0);
        return result;
    }

    /**
     * Inventory analytics: status breakdown, warehouse distribution.
     */
    public Map<String, Object> getInventoryAnalytics() {
        List<Inventory> inventoryList = inventoryRepository.findAll();

        Map<String, Long> statusCounts = inventoryList.stream()
                .collect(Collectors.groupingBy(Inventory::getStatus, Collectors.counting()));

        Map<String, Long> warehouseCounts = inventoryList.stream()
                .collect(Collectors.groupingBy(Inventory::getWarehouseLocation, Collectors.counting()));

        Map<String, Object> result = new HashMap<>();
        result.put("totalInventory", inventoryList.size());
        result.put("statusBreakdown", statusCounts);
        result.put("warehouseDistribution", warehouseCounts);
        return result;
    }

    /**
     * Dealer analytics: count by region, aggregate performance.
     */
    public Map<String, Object> getDealerAnalytics() {
        List<Dealer> dealers = dealerRepository.findAll();

        Map<String, Long> regionCounts = dealers.stream()
                .collect(Collectors.groupingBy(Dealer::getRegion, Collectors.counting()));

        Map<String, Object> result = new HashMap<>();
        result.put("totalDealers", dealers.size());
        result.put("regionBreakdown", regionCounts);
        return result;
    }
}
