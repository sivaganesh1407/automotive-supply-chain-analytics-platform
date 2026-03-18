package com.automotive.platform.repository;

import com.automotive.platform.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository for Dealer entity - handles database operations.
 */
@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {

    List<Dealer> findByRegion(String region);

    List<Dealer> findByNameContainingIgnoreCase(String name);
}
