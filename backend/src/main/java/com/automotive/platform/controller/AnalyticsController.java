package com.automotive.platform.controller;

import com.automotive.platform.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller for Analytics endpoints.
 */
@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    /**
     * GET /analytics/production - production efficiency, defect rates, totals.
     */
    @GetMapping("/production")
    public ResponseEntity<Map<String, Object>> getProductionAnalytics() {
        Map<String, Object> analytics = analyticsService.getProductionAnalytics();
        return ResponseEntity.ok(analytics);
    }

    /**
     * GET /analytics/inventory - inventory status breakdown, warehouse distribution.
     */
    @GetMapping("/inventory")
    public ResponseEntity<Map<String, Object>> getInventoryAnalytics() {
        Map<String, Object> analytics = analyticsService.getInventoryAnalytics();
        return ResponseEntity.ok(analytics);
    }

    /**
     * GET /analytics/dealers - dealer performance, region breakdown.
     */
    @GetMapping("/dealers")
    public ResponseEntity<Map<String, Object>> getDealerAnalytics() {
        Map<String, Object> analytics = analyticsService.getDealerAnalytics();
        return ResponseEntity.ok(analytics);
    }
}
