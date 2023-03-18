package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.domain.Game;
import com.gamersdirectory.gamersapi.dto.GameDTO;
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
    public GameDTO getGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Game not found."));

        return new GameDTO(game);
    }

    @Override
    public List<GameDTO> listAll() {
        List<Game> games = gameRepository.findAll();

        return games.stream().map(GameDTO::new).toList();
    }

}
