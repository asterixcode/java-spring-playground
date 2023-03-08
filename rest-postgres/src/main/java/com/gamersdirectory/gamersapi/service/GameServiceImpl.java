package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.domain.Game;
import com.gamersdirectory.gamersapi.exception.ApiNotFoundException;
import com.gamersdirectory.gamersapi.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

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
