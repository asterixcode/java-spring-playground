package com.gamersdirectory.gamersapi.controller;

import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.dto.AccountInputDTO;
import com.gamersdirectory.gamersapi.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/account")
@Tag(name="Account", description = "Account related endpoints.")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "Create new account", responses = {
            @ApiResponse(responseCode = "201", ref = "accountCreatedSuccessfully"),
            @ApiResponse(responseCode = "400", ref = "badRequestApi")})
    public ResponseEntity<AccountDTO> signup(
//            @io.swagger.v3.oas.annotations.parameters.RequestBody(
//                description = "Account details.",
//                required = true,
//                content = @Content(
//                        mediaType = "application/json",
//                        schema = @Schema(implementation = AccountInputDTO.class),
//                        examples = {
//                                @ExampleObject(
//                                        name = "Account details.",
//                                        value = """
//                                                {
//                                                    "name": "John Doe",
//                                                    "nickname": "john-john",
//                                                    "location": "Europe",
//                                                    "interests": [
//                                                      {
//                                                        "game": "League of Legends",
//                                                        "level": "INTERMEDIATE"
//                                                      },
//                                                      {
//                                                        "game": "Counter-Strike",
//                                                        "level": "GODLIKE`"
//                                                      }
//                                                    ]
//                                                }
//                                                """)
//                        }))
            @RequestBody @Valid AccountInputDTO accountInputDTO,
            UriComponentsBuilder uriBuilder) {
        AccountDTO accountDTO = accountService.save(accountInputDTO);
        URI uri = uriBuilder.path("api/v1/account/{id}").buildAndExpand(accountDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(accountDTO);
    }

    @GetMapping("/{id}")
//    @Operation(summary = "Get account by Id", responses = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "OK: Account details.",
//                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDTO.class))),
//            @ApiResponse(
//                    responseCode = "404",
//                    description = "Not Found: Account id was not found.",
//                    content = @Content(
//                            mediaType = "application/json",
//                            examples = {
//                                    @ExampleObject(
//                                            name = "Bad Request. Verify requested fields.",
//                                            value = """
//                                                            {
//                                                              "message": "",
//                                                              "httpStatus": "BAD_REQUEST"
//                                                              "timestamp": "2021-07-19T19:00:00.000+00:00",
//                                                            }
//                                                            """
//                                    )
//                            },
//                            schema = @Schema(implementation = ApiException.class)
//                    ))
//    })
    public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.findById(id);

        return ResponseEntity.ok(accountDTO);
    }

}
