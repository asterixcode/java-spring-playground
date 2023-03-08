package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.domain.Account;
import com.gamersdirectory.gamersapi.domain.Game;
import com.gamersdirectory.gamersapi.domain.Location;
import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.dto.AccountInputDTO;
import com.gamersdirectory.gamersapi.exception.ApiNotFoundException;
import com.gamersdirectory.gamersapi.repository.AccountRepository;
import com.gamersdirectory.gamersapi.repository.GameRepository;
import com.gamersdirectory.gamersapi.repository.LocationRepository;
import com.gamersdirectory.gamersapi.utils.CustomModelMapper;
import com.gamersdirectory.gamersapi.utils.ErrorMessageEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final LocationRepository locationRepository;
    private final GameRepository gameRepository;
    private CustomModelMapper modelMapper;

    @Override
    public AccountDTO save(AccountInputDTO accountInputDTO) {
        Location findLocation = validateLocation(accountInputDTO.getLocation());

        Account account = new Account();

        account.setName(accountInputDTO.getName());
        account.setNickname(accountInputDTO.getNickname());
        account.setEmail(accountInputDTO.getEmail());
        account.setLocation(findLocation);

        if (Objects.nonNull(accountInputDTO.getGames())) {
            List<Game> games = new ArrayList<>();
            accountInputDTO.getGames().forEach(gameDTO -> {
                Game game = gameRepository.findByName(gameDTO.getName())
                        .orElseThrow(() -> new ApiNotFoundException(
                                String.format(ErrorMessageEnum.GAME_NOT_FOUND.getMessage(), gameDTO.getName())));
                games.add(game);
            });
            account.setGames(games);
        }

        return modelMapper.map(accountRepository.save(account), AccountDTO.class);
    }

    private Location validateLocation(String continent) {
        return locationRepository.findLocationByContinent(continent)
                .orElseThrow(() -> new ApiNotFoundException(
                        String.format(ErrorMessageEnum.LOCATION_NOT_FOUND.getMessage(), continent)));
    }

    @Override
    public AccountDTO findById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ApiNotFoundException(
                        String.format(ErrorMessageEnum.ACCOUNT_ID_NOT_FOUND.getMessage(), accountId)
                ));
        return modelMapper.map(account, AccountDTO.class);
    }
}
