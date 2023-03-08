package com.gamersdirectory.gamersapi.controller;

import com.gamersdirectory.gamersapi.data.request.GameRequest;
import com.gamersdirectory.gamersapi.data.response.WebResponse;
import com.gamersdirectory.gamersapi.domain.Game;
import com.gamersdirectory.gamersapi.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/game")
@Tag(name="Game")
public class GameController {

    private final GameService gameService;

    @GetMapping("/all")
    public List<Game> getAllGames() {
        return gameService.listAll();
    }

    @GetMapping("/top5")
    public List<Game> getListOf5Games() {
        return gameService.listTop5Games();
    }

    @PostMapping("")
    @Operation(
            description = "Create new game",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created game!"),
                    @ApiResponse(responseCode = "400", ref = "badRequestApi"),
                    @ApiResponse(responseCode = "404", ref = "notFoundApi"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    public ResponseEntity<WebResponse> createGame(@RequestBody GameRequest name) {
        return new ResponseEntity<>(new WebResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), "Game created successfully"), HttpStatus.CREATED);
    }

}
