package com.gamersdirectory.gamersapi.repository;

import com.gamersdirectory.gamersapi.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM GAME ORDER BY id LIMIT 5")
    Optional<List<Game>> findTopFiveGames();

}
