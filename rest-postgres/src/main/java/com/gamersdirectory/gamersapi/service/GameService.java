package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.domain.Game;

import java.util.List;

public interface GameService {

    List<Game> listAll();

    List<Game> listTop5Games();
}
