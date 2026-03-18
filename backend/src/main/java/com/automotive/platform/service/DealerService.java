package com.automotive.platform.service;

import com.automotive.platform.model.Dealer;
import com.automotive.platform.repository.DealerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for Dealer operations - business logic and orchestration.
 */
@Service
public class DealerService {

    private final DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public List<Dealer> findAll() {
        return dealerRepository.findAll();
    }

    public Optional<Dealer> findById(Long id) {
        return dealerRepository.findById(id);
    }

    public Dealer save(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    public List<Dealer> findByRegion(String region) {
        return dealerRepository.findByRegion(region);
    }

    public List<Dealer> findByNameContaining(String name) {
        return dealerRepository.findByNameContainingIgnoreCase(name);
    }
}
