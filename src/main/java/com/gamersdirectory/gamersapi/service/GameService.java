package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.entity.Game;
import java.util.List;

public interface GameService {

    List<Game> listAll();

    List<Game> listTop5Games();
}
