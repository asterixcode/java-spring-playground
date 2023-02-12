package com.gamersdirectory.gamersapi.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext // control the application context cache between tests
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // starts the server with random port to avoid conflicts in test environment
@AutoConfigureMockMvc
class IndexControllerTest {

    @LocalServerPort // inject the port
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IndexController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void greetingShouldReturnDefaultMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/test", String.class)).contains("GAMERS API");
    }

    @Test
    @DisplayName("Should Return \"GAMERS API\" for /test endpoint")
    void shouldReturnGreeting_WhenCallingGetEndpoint() throws Exception {
        MvcResult requestResult = mockMvc.perform(MockMvcRequestBuilders.get("/test"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("GAMERS API")).andReturn();

        // TODO: add assertions
    }
}