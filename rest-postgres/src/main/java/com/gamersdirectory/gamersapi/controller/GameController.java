package com.gamersdirectory.gamersapi.controller;

import com.gamersdirectory.gamersapi.data.request.GameRequest;
import com.gamersdirectory.gamersapi.data.response.WebResponse;
import com.gamersdirectory.gamersapi.dto.GameDTO;
import com.gamersdirectory.gamersapi.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Get game by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved game",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GameDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Game not found" ,content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGame(id));
    }

    @Operation(description = "Get all games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GameDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all")
    public List<GameDTO> getAllGames() {
        return gameService.listAll();
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
