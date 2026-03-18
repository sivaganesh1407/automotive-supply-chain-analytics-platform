package com.automotive.platform.controller;

import com.automotive.platform.model.Vehicle;
import com.automotive.platform.repository.VehicleRepository;
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
class VehicleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void getVehicles_returnsList() throws Exception {
        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getVehicles_returnsVehicles_whenDataExists() throws Exception {
        Vehicle v = new Vehicle("Sedan X1", "Detroit", LocalDate.of(2025, 3, 15));
        vehicleRepository.save(v);

        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[*].model", hasItem("Sedan X1")))
                .andExpect(jsonPath("$[*].plantLocation", hasItem("Detroit")));
    }

    @Test
    void getVehicleById_returns404_whenNotFound() throws Exception {
        mockMvc.perform(get("/vehicles/99999"))
                .andExpect(status().isNotFound());
    }
}
