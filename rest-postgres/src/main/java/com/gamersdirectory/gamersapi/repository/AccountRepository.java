package com.gamersdirectory.gamersapi.repository;

import com.gamersdirectory.gamersapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Account a WHERE a.id = ?1")
    int deleteAccountById(Long id);
}
