package com.georgesdoe.budgeteer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI budgeteerOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Budgeteer API")
                .description("REST API for managing accounts, categories, transactions "
                        + "and importing transactions from bank statement files.")
                .version("v0.0.1"));
    }
}
