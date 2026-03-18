package com.automotive.platform.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Entity representing a dealer in the automotive supply chain.
 */
@Entity
@Table(name = "dealers")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Dealer name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Region is required")
    @Column(nullable = false)
    private String region;

    public Dealer() {
    }

    public Dealer(String name, String region) {
        this.name = name;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
