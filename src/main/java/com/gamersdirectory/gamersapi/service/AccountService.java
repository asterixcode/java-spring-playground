package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.dto.InterestDTO;
import com.gamersdirectory.gamersapi.validation.AccountForm;

import java.util.List;

public interface AccountService {
    AccountDTO save(AccountForm accountForm);
    AccountDTO findById(Long accountId);

    List<InterestDTO> findInterestsByAccountId(Long id);
}
