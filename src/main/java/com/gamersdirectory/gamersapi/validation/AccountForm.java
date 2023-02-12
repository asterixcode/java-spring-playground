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
    @Size(max = 50)
    private String name;

    @NotEmpty
    @Size(min = 4, max = 15, message = "Nickname size must be between 4 and 15 characters.")
    private String nickname;

    @NotEmpty
    private String location;

    private List<InterestDTO> interests;

}
