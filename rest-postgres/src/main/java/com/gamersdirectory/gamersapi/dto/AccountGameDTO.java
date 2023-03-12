package com.gamersdirectory.gamersapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountGameDTO {
    private String name;
    private String level;
}
