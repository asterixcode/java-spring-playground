package com.gamersdirectory.gamersapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldSignUpAndSaveAccount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/account")
                .content("{\"name\": \"Test Name\", \"nickname\": \"Test Nickname\", \"location\": \"asia\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header()
                        .string("Location", "http://localhost/api/v1/account/1"))
                .andExpect(MockMvcResultMatchers.header()
                        .string("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"name\":\"Test Name\",\"nickname\":\"Test Nickname\",\"location\":\"asia\",\"interests\":[]}"));
    }
}