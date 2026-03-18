package com.automotive.platform.controller;

import com.automotive.platform.model.Inventory;
import com.automotive.platform.model.ProductionMetrics;
import com.automotive.platform.model.Vehicle;
import com.automotive.platform.repository.InventoryRepository;
import com.automotive.platform.repository.ProductionMetricsRepository;
import com.automotive.platform.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class AnalyticsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductionMetricsRepository productionMetricsRepository;

    @BeforeEach
    void setUp() {
        Vehicle v = vehicleRepository.save(new Vehicle("Sedan", "Detroit", LocalDate.of(2025, 3, 15)));
        inventoryRepository.save(new Inventory(v.getId(), "IN_STOCK", "Warehouse A"));
        productionMetricsRepository.save(new ProductionMetrics(v.getId(), 45.0, 0));
    }

    @Test
    void getProductionAnalytics_returnsMetrics() throws Exception {
        mockMvc.perform(get("/analytics/production"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalVehicles").value(greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.totalDefects").exists())
                .andExpect(jsonPath("$.defectRate").exists())
                .andExpect(jsonPath("$.efficiencyScore").exists());
    }

    @Test
    void getInventoryAnalytics_returnsBreakdown() throws Exception {
        mockMvc.perform(get("/analytics/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalInventory").value(greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.statusBreakdown").exists())
                .andExpect(jsonPath("$.warehouseDistribution").exists());
    }

    @Test
    void getDealerAnalytics_returnsBreakdown() throws Exception {
        mockMvc.perform(get("/analytics/dealers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalDealers").exists())
                .andExpect(jsonPath("$.regionBreakdown").exists());
    }
}
