package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.dto.AccountInputDTO;

public interface AccountService {
    AccountDTO save(AccountInputDTO accountInputDTO);
    AccountDTO findById(Long accountId);

}
