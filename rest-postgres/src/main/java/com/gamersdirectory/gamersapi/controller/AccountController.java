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
    @Operation(summary = "Create new account",
            responses = {
            @ApiResponse(responseCode = "201", ref = "accountCreatedSuccessfully"),
            @ApiResponse(responseCode = "400", ref = "badRequestApi")})
    public ResponseEntity<AccountDTO> signup(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(ref = "accountInputDTO")
            @RequestBody @Valid AccountInputDTO accountInputDTO, UriComponentsBuilder uriBuilder) {
        AccountDTO accountDTO = accountService.save(accountInputDTO);
        URI uri = uriBuilder.path("api/v1/account/{id}").buildAndExpand(accountDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(accountDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve account by Id", responses = {
            @ApiResponse(responseCode = "200", ref = "accountFound"),
            @ApiResponse(responseCode = "400", ref = "badRequestApi"),
            @ApiResponse(responseCode = "404", ref = "notFoundApi")})
    public ResponseEntity<AccountDTO> getById(@PathVariable("id") Long id) {
        AccountDTO accountDTO = accountService.findById(id);
        return ResponseEntity.ok(accountDTO);
    }

}
