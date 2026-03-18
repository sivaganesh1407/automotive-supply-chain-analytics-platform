package com.automotive.platform.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class ETLControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void runEtl_loadsDataAndReturnsCounts() throws Exception {
        String payload = "{\"vehicles\":[{\"model\":\"Sedan X1\",\"plantLocation\":\"Detroit\",\"productionDate\":\"2025-03-15\"},"
                + "{\"model\":\"SUV Y2\",\"plantLocation\":\"Michigan\",\"productionDate\":\"2025-03-16\"}],"
                + "\"inventory\":[{\"vehicleId\":1,\"status\":\"IN_STOCK\",\"warehouseLocation\":\"Warehouse A\"},"
                + "{\"vehicleId\":2,\"status\":\"IN_TRANSIT\",\"warehouseLocation\":\"Warehouse B\"}],"
                + "\"dealers\":[{\"name\":\"Metro Auto\",\"region\":\"North\"}],"
                + "\"productionMetrics\":[{\"vehicleId\":1,\"productionTime\":45.5,\"defectsCount\":0},"
                + "{\"vehicleId\":2,\"productionTime\":52.3,\"defectsCount\":1}]}";

        mockMvc.perform(post("/etl/run")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("ETL completed successfully"))
                .andExpect(jsonPath("$.vehiclesLoaded").value(2))
                .andExpect(jsonPath("$.inventoryLoaded").value(2))
                .andExpect(jsonPath("$.dealersLoaded").value(1))
                .andExpect(jsonPath("$.productionMetricsLoaded").value(2));
    }

    @Test
    void runEtl_invalidData_returns400() throws Exception {
        String invalidPayload = "{\"vehicles\":[{\"model\":\"\",\"plantLocation\":\"Detroit\",\"productionDate\":\"2025-03-15\"}]}";

        mockMvc.perform(post("/etl/run")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation Failed"));
    }
}
