package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.entity.Game;
import com.gamersdirectory.gamersapi.exception.ApiNotFoundException;
import com.gamersdirectory.gamersapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> listAll() {
        return gameRepository.findAll();
    }

    @Override
    public List<Game> listTop5Games() {
        return gameRepository.findTopFiveGames()
                .orElseThrow(() -> new ApiNotFoundException("List of games is empty."));
    }
}
