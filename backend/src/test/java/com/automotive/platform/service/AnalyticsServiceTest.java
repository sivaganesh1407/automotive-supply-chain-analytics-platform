package com.automotive.platform.service;

import com.automotive.platform.model.Dealer;
import com.automotive.platform.model.Inventory;
import com.automotive.platform.model.ProductionMetrics;
import com.automotive.platform.model.Vehicle;
import com.automotive.platform.repository.DealerRepository;
import com.automotive.platform.repository.InventoryRepository;
import com.automotive.platform.repository.ProductionMetricsRepository;
import com.automotive.platform.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalyticsServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private DealerRepository dealerRepository;
    @Mock
    private ProductionMetricsRepository productionMetricsRepository;

    @InjectMocks
    private AnalyticsService analyticsService;

    @Test
    void getProductionAnalytics_returnsCorrectMetrics() {
        Vehicle v = new Vehicle("Sedan", "Detroit", LocalDate.of(2025, 3, 15));
        v.setId(1L);
        ProductionMetrics m = new ProductionMetrics(1L, 45.0, 0);
        when(vehicleRepository.findAll()).thenReturn(Arrays.asList(v));
        when(productionMetricsRepository.findAll()).thenReturn(Arrays.asList(m));

        Map<String, Object> result = analyticsService.getProductionAnalytics();

        assertThat(result.get("totalVehicles")).isEqualTo(1);
        assertThat(result.get("totalProductionTime")).isEqualTo(45.0);
        assertThat(result.get("totalDefects")).isEqualTo(0);
        assertThat(result).containsKeys("defectRate", "efficiencyScore");
    }

    @Test
    void getProductionAnalytics_emptyData_returnsZeroValues() {
        when(vehicleRepository.findAll()).thenReturn(Collections.emptyList());
        when(productionMetricsRepository.findAll()).thenReturn(Collections.emptyList());

        Map<String, Object> result = analyticsService.getProductionAnalytics();

        assertThat(result.get("totalVehicles")).isEqualTo(0);
        assertThat(result.get("totalDefects")).isEqualTo(0);
    }

    @Test
    void getInventoryAnalytics_returnsStatusBreakdown() {
        Inventory inv = new Inventory(1L, "IN_STOCK", "Warehouse A");
        when(inventoryRepository.findAll()).thenReturn(Arrays.asList(inv));

        Map<String, Object> result = analyticsService.getInventoryAnalytics();

        assertThat(result.get("totalInventory")).isEqualTo(1);
        @SuppressWarnings("unchecked")
        Map<String, Long> statusBreakdown = (Map<String, Long>) result.get("statusBreakdown");
        assertThat(statusBreakdown.get("IN_STOCK")).isEqualTo(1L);
    }

    @Test
    void getDealerAnalytics_returnsRegionBreakdown() {
        Dealer d = new Dealer("Metro Auto", "North");
        when(dealerRepository.findAll()).thenReturn(Arrays.asList(d));

        Map<String, Object> result = analyticsService.getDealerAnalytics();

        assertThat(result.get("totalDealers")).isEqualTo(1);
        @SuppressWarnings("unchecked")
        Map<String, Long> regionBreakdown = (Map<String, Long>) result.get("regionBreakdown");
        assertThat(regionBreakdown.get("North")).isEqualTo(1L);
    }
}
