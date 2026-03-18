package com.automotive.platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Root controller - provides API info and prevents 404 on root path.
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home() {
        return ResponseEntity.ok(Map.of(
                "application", "Automotive Supply Chain Analytics Platform",
                "status", "running",
                "endpoints", Map.of(
                        "vehicles", "GET /vehicles",
                        "inventory", "GET /inventory",
                        "dealers", "GET /dealers",
                        "analytics/production", "GET /analytics/production",
                        "analytics/inventory", "GET /analytics/inventory",
                        "analytics/dealers", "GET /analytics/dealers",
                        "etl/run", "POST /etl/run",
                        "h2-console", "GET /h2-console"
                )
        ));
    }
}
