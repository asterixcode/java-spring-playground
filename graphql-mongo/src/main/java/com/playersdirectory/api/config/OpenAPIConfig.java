package com.playersdirectory.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition
public class OpenAPIConfig {

    private static final String API_TITLE = "GraphQL API";
    private static final String API_DESCRIPTION = "Players Directory Service";
    private static final String API_VERSION = "1.0.0";

    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI()
                .info(apiInfo());
    }

    public Info apiInfo() {
        Info info = new Info();
        info
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION);
        return info;
    }
}
