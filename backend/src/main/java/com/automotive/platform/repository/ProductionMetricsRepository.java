package com.automotive.platform.repository;

import com.automotive.platform.model.ProductionMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository for ProductionMetrics entity - handles database operations.
 */
@Repository
public interface ProductionMetricsRepository extends JpaRepository<ProductionMetrics, Long> {

    List<ProductionMetrics> findByVehicleId(Long vehicleId);
}
