package com.automotive.platform.controller;

import com.automotive.platform.model.Dealer;
import com.automotive.platform.service.DealerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Dealer endpoints.
 */
@RestController
@RequestMapping("/dealers")
public class DealerController {

    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    /**
     * GET /dealers - returns all dealers.
     */
    @GetMapping
    public ResponseEntity<List<Dealer>> getAllDealers() {
        List<Dealer> dealers = dealerService.findAll();
        return ResponseEntity.ok(dealers);
    }

    /**
     * GET /dealers/{id} - returns a single dealer by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Dealer> getDealerById(@PathVariable Long id) {
        return dealerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
