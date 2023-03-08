package com.gamersdirectory.gamersapi.repository;

import com.gamersdirectory.gamersapi.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findLocationByContinent(String continent);

}
