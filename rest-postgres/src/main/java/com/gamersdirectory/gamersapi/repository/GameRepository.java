package com.gamersdirectory.gamersapi.repository;

import com.gamersdirectory.gamersapi.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE g.name = ?1")
    Optional<Game> findByName(String name);

}
