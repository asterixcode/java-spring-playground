package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.domain.*;
import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.dto.AccountInputDTO;
import com.gamersdirectory.gamersapi.exception.ApiNotFoundException;
import com.gamersdirectory.gamersapi.repository.*;
import com.gamersdirectory.gamersapi.utils.CustomModelMapper;
import com.gamersdirectory.gamersapi.utils.ErrorMessageEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final LocationRepository locationRepository;
    private CustomModelMapper modelMapper;

    @Override
    public AccountDTO save(AccountInputDTO accountInputDTO) {
        Location findLocation = validateLocation(accountInputDTO.getLocation());

        Account account = new Account();
        account.setName(accountInputDTO.getName());
        account.setNickname(accountInputDTO.getNickname());
        account.setEmail(accountInputDTO.getEmail());
        account.setPassword(accountInputDTO.getPassword());
        account.setLocation(findLocation);

        accountRepository.save(account);

        return modelMapper.map(account, AccountDTO.class);
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
