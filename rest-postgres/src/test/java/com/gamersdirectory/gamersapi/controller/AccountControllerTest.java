//package com.gamersdirectory.gamersapi.controller;
//
//import com.gamersdirectory.gamersapi.dto.AccountDTO;
//import com.gamersdirectory.gamersapi.service.AccountService;
//import io.restassured.http.ContentType;
//import io.restassured.module.mockmvc.RestAssuredMockMvc;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Collections;
//
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(AccountController.class)
//class AccountControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private AccountController accountController;
//
//    @MockBean
//    private AccountService accountService;
//
//    @BeforeEach
//    public void setup() {
//        // spin up the spring context with only the necessary controllers/services/... listed below
//        // in this case, we inject only the account controller, to speed up the test by not injecting into the spring context unnecessary beans
//        // here we specify which beans we want, and we inject into the context with @Autowired as private field
//        RestAssuredMockMvc.standaloneSetup(this.accountController);
//    }
//
//    @DirtiesContext
//    @Test
//    void shouldCreateNewAccount_WhenSignUp() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/account")
//                .content("{\"name\": \"Test Name\", \"nickname\": \"Test Nickname\", \"location\": \"asia\" }")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.header()
//                        .string("Location", "http://localhost/api/v1/account/1"))
//                .andExpect(MockMvcResultMatchers.header()
//                        .string("Content-Type", "application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string("{\"id\":1,\"name\":\"Test Name\",\"nickname\":\"Test Nickname\",\"location\":\"asia\",\"interests\":[]}"));
//    }
//
//    @Test
//    void shouldReturnSuccess_WhenGetAccountById() {
//        when(this.accountService.findById(1L))
//                .thenReturn(new AccountDTO(1L, "test name", "test nickname", "europe", Collections.emptyList()));
//
//        RestAssuredMockMvc
//                .given().accept(ContentType.JSON)
//                .when().get("/api/v1/account/{id}", 1L)
//                .then().statusCode(HttpStatus.OK.value());
//    }
//}