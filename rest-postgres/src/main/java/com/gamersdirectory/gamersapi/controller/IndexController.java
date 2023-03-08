package com.gamersdirectory.gamersapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name="Index")
public class IndexController {

    @GetMapping
    public String get() {
        return "GAMERS API";
    }
}
