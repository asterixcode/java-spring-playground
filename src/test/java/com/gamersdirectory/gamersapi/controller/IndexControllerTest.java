package com.gamersdirectory.gamersapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext // control the application context cache between tests
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // starts the server with random port to avoid conflicts in test environment
class IndexControllerTest {

    @LocalServerPort // inject the port
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IndexController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void greetingShouldReturnDefaultMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/test", String.class)).contains("GAMERS API");
    }
}