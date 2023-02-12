package com.gamersdirectory.gamersapi.validation;

import com.gamersdirectory.gamersapi.dto.InterestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm {

    @NotEmpty(message = "Name is required.")
    @Size(min = 1, max = 50, message = "Name size must be between 1 and 50 characters.")
    private String name;

    @NotEmpty(message = "Nickname is required.")
    @Size(min = 4, max = 15, message = "Nickname size must be between 4 and 15 characters.")
    private String nickname;

    @NotEmpty(message = "Location is required.")
    private String location;

    private List<InterestDTO> interests;
}
