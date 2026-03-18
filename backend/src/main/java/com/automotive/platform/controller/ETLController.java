package com.automotive.platform.controller;

import com.automotive.platform.dto.EtlDataRequest;
import com.automotive.platform.service.ETLService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for ETL pipeline - simulates JSON data ingestion.
 */
@RestController
@RequestMapping("/etl")
public class ETLController {

    private final ETLService etlService;

    public ETLController(ETLService etlService) {
        this.etlService = etlService;
    }

    /**
     * POST /etl/run - runs ETL pipeline with JSON payload.
     * Validates, transforms, and loads data into the database.
     */
    @PostMapping("/run")
    public ResponseEntity<Map<String, Object>> runEtl(@Valid @RequestBody EtlDataRequest request) {
        Map<String, Object> result = etlService.runEtl(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
