package com.automotive.platform.service;

import com.automotive.platform.model.Vehicle;
import com.automotive.platform.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void findAll_returnsAllVehicles() {
        Vehicle v1 = new Vehicle("Sedan", "Detroit", LocalDate.of(2025, 3, 15));
        v1.setId(1L);
        when(vehicleRepository.findAll()).thenReturn(Arrays.asList(v1));

        List<Vehicle> result = vehicleService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getModel()).isEqualTo("Sedan");
        verify(vehicleRepository).findAll();
    }

    @Test
    void findById_whenExists_returnsVehicle() {
        Vehicle v = new Vehicle("SUV", "Michigan", LocalDate.of(2025, 3, 16));
        v.setId(1L);
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(v));

        Optional<Vehicle> result = vehicleService.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getModel()).isEqualTo("SUV");
    }

    @Test
    void findById_whenNotExists_returnsEmpty() {
        when(vehicleRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Vehicle> result = vehicleService.findById(999L);

        assertThat(result).isEmpty();
    }

    @Test
    void save_persistsVehicle() {
        Vehicle input = new Vehicle("Truck", "Ohio", LocalDate.of(2025, 3, 17));
        Vehicle saved = new Vehicle("Truck", "Ohio", LocalDate.of(2025, 3, 17));
        saved.setId(1L);
        when(vehicleRepository.save(input)).thenReturn(saved);

        Vehicle result = vehicleService.save(input);

        assertThat(result.getId()).isEqualTo(1L);
        verify(vehicleRepository).save(input);
    }
}
