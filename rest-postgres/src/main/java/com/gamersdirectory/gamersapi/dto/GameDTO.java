package com.gamersdirectory.gamersapi.dto;

import com.gamersdirectory.gamersapi.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameDTO {

    private Long id;
    private String name;

    public GameDTO(Game game) {
        this.id = game.getId();
        this.name = game.getName();
    }
}
