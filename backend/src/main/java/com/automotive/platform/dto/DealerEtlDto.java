package com.automotive.platform.dto;

import javax.validation.constraints.NotBlank;

/**
 * DTO for Dealer data in ETL JSON payload.
 */
public class DealerEtlDto {

    @NotBlank(message = "Dealer name is required")
    private String name;

    @NotBlank(message = "Region is required")
    private String region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
