package com.gamersdirectory.gamersapi.controller;

import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.dto.InterestDTO;
import com.gamersdirectory.gamersapi.validation.AccountForm;
import com.gamersdirectory.gamersapi.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created successfully.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request. Verify requested fields.",
                    content = @Content)
    })
    public ResponseEntity<AccountDTO> signup(@RequestBody @Valid AccountForm accountForm, UriComponentsBuilder uriBuilder) {
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
                    content = @Content)
    })
    public AccountDTO getById(@PathVariable Long id) {
        return accountService.findById(id);
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
    public List<InterestDTO> getInterestsByAccountId(@PathVariable  Long accountId) { return accountService.findInterestsByAccountId(accountId); }
}
