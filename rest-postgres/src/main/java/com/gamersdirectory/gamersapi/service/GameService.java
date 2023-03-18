package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.dto.GameDTO;

import java.util.List;

public interface GameService {

    GameDTO getGame(Long id);
    List<GameDTO> listAll();
}
