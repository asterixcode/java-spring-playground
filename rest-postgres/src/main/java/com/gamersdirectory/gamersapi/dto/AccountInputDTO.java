package com.gamersdirectory.gamersapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountInputDTO {

    @NotEmpty(message = "Name is required.")
    @Size(min = 1, max = 50, message = "Name size must be between 1 and 50 characters.")
    private String name;

    @NotEmpty(message = "Nickname is required.")
    @Size(min = 4, max = 15, message = "Nickname size must be between 4 and 15 characters.")
    private String nickname;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Email is invalid.")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Size(min = 8, max = 20, message = "Password size must be between 8 and 20 characters.")
    private String password;

    @NotEmpty(message = "Location is required.")
    private String location;
}
