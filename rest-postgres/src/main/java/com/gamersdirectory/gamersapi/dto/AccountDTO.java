package com.gamersdirectory.gamersapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {

    private Long id;
    private String name;
    private String nickname;
    private String email;
    private LocationDTO location;
    private List<AccountGameDTO> games;

}
