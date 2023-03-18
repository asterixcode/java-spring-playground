package com.gamersdirectory.gamersapi.repository;

import com.gamersdirectory.gamersapi.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(
            value = "SELECT g FROM Game g WHERE g.name = :name",
            nativeQuery = true
    )
    Optional<Game> findByName(@Param("name") String name);

}
