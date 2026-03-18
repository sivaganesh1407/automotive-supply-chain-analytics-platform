package com.automotive.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Automotive Supply Chain Analytics Platform.
 * Bootstraps the Spring Boot application with all auto-configured components.
 */
@SpringBootApplication
public class AutomotivePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomotivePlatformApplication.class, args);
    }
}
