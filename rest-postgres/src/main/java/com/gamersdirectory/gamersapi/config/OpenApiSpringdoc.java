package com.gamersdirectory.gamersapi.config;

import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.exception.ApiException;
import com.gamersdirectory.gamersapi.utils.ReadJsonFileToJsonObject;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@OpenAPIDefinition
public class OpenApiSpringdoc {

    private static final String API_TITLE = "Gamers Directory API";
    private static final String API_DESCRIPTION = "Gamers Directory API";
    private static final String API_VERSION = "1.0.0";
    private static final String DEFAULT_RESPONSE_KEY = "default";

    @Bean
    public OpenAPI baseOpenApi() throws IOException {

        ReadJsonFileToJsonObject readJsonFileToJsonObject = new ReadJsonFileToJsonObject();

        ApiResponse accountCreatedSuccessfully = new ApiResponse()
                .description("Account created successfully.")
                .content(new Content()
                        .addMediaType(APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType()
                                .addExamples(DEFAULT_RESPONSE_KEY,
                                        new Example().value(readJsonFileToJsonObject.read().get("accountCreatedSuccessfullyResponse").toString()))
                                .schema(new Schema<AccountDTO>().example(AccountDTO.class)))
                );

        ApiResponse badRequestApi = new ApiResponse()
                .description("Bad Request. Verify requested fields.")
                .content(new Content()
                        .addMediaType(APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType()
                                .addExamples(DEFAULT_RESPONSE_KEY,
                                        new Example().value(readJsonFileToJsonObject.read().get("badRequestResponse").toString()))
                                .schema(new Schema<ApiException>().example(ApiException.class)))
                );

        ApiResponse notFoundApi = new ApiResponse()
                .content(new Content().addMediaType(APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType()
                                .addExamples(DEFAULT_RESPONSE_KEY,
                                        new Example().value(readJsonFileToJsonObject.read().get("notFoundResponse").toString()))
                ));

        Components components = new Components();
        components.addResponses("accountCreatedSuccessfully", accountCreatedSuccessfully);
        components.addResponses("badRequestApi", badRequestApi);
        components.addResponses("notFoundApi", notFoundApi);

        return new OpenAPI()
                .components(components)
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

    @Bean
    public GroupedOpenApi authenticationApi() {
        String [] paths = {"/api/v1/account/**"};
        return GroupedOpenApi.builder()
                .group("Authentication")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi gameApi() {
        String [] paths = {"/api/v1/game/**"};
        return GroupedOpenApi.builder()
                .group("Game")
                .pathsToMatch(paths)
                .build();
    }
}
