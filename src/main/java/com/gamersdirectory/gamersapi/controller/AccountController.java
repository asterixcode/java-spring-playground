package com.gamersdirectory.gamersapi.controller;

import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.dto.InterestDTO;
import com.gamersdirectory.gamersapi.exception.ApiException;
import com.gamersdirectory.gamersapi.validation.AccountForm;
import com.gamersdirectory.gamersapi.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/account")
@Tag(name="Account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @Operation(summary = "Create new account", responses = {
            @ApiResponse(responseCode = "201", ref = "accountCreatedSuccessfully"),
            @ApiResponse(responseCode = "400", ref = "badRequestApi")})
    public ResponseEntity<AccountDTO> signup(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Account details.",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = AccountForm.class),
                        examples = {
                                @ExampleObject(
                                        name = "Account details.",
                                        value = """
                                                {
                                                    "name": "John Doe",
                                                    "nickname": "john-john",
                                                    "location": "Europe",
                                                    "interests": [
                                                      {
                                                        "game": "League of Legends",
                                                        "level": "INTERMEDIATE"
                                                      },
                                                      {
                                                        "game": "Counter-Strike",
                                                        "level": "GODLIKE`"
                                                      }
                                                    ]
                                                }
                                                """)
                        }))
            @RequestBody @Valid AccountForm accountForm,
            UriComponentsBuilder uriBuilder) {
        AccountDTO accountDTO = accountService.save(accountForm);
        URI uri = uriBuilder.path("api/v1/account/{id}").buildAndExpand(accountDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(accountDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by Id", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK: Account details.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found: Account Id was not found.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Bad Request. Verify requested fields.",
                                            value = """
                                                            {
                                                              "message": "",
                                                              "httpStatus": "BAD_REQUEST"
                                                              "timestamp": "2021-07-19T19:00:00.000+00:00",
                                                            }
                                                            """
                                    )
                            },
                            schema = @Schema(implementation = ApiException.class)
                    ))
    })
    public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.findById(id);

        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/interests/{accountId}")
    @Operation(summary = "Get list of interests by account Id", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK: List of interests containing each game and the respective level of a specific account.",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = InterestDTO.class)))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found: Account Id was not found.",
                    content = @Content)
    })
    public List<InterestDTO> getInterestsByAccountId(@PathVariable  Long accountId) {
        return accountService.findInterestsByAccountId(accountId);
    }
}
