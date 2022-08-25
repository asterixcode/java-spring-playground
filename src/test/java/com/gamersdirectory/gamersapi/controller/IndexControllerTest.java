package com.gamersdirectory.gamersapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IndexControllerTest {

    @Autowired
    private IndexController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}