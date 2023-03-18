package com.gamersdirectory.gamersapi.repository;

import com.gamersdirectory.gamersapi.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Optional<Level> findByName(String level);
}
