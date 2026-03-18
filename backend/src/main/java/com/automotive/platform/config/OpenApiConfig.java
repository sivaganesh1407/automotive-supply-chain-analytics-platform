package com.automotive.platform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI / Swagger configuration.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Automotive Supply Chain Analytics Platform API")
                        .version("1.0")
                        .description("REST API for automotive manufacturing data pipelines, ETL processing, and analytics")
                        .contact(new Contact()
                                .name("Platform Team")));
    }
}
