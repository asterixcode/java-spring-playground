package com.gamersdirectory.gamersapi.repository;

import com.gamersdirectory.gamersapi.domain.AccountGame;
import com.gamersdirectory.gamersapi.domain.AccountGameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountGameRepository extends JpaRepository<AccountGame, AccountGameId> {
}
