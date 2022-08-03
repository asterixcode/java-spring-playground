package com.gamersdirectory.gamersapi.controller;

import com.gamersdirectory.gamersapi.entity.Game;
import com.gamersdirectory.gamersapi.service.GameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/game")
@Tag(name="Game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/all")
    public List<Game> getAllGames() {
        return gameService.listAll();
    }

    @GetMapping("/top5")
    public List<Game> getListOf5Games() {
        return gameService.listTop5Games();
    }

}
