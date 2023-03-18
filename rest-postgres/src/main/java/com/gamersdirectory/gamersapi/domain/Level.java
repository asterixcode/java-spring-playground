package com.gamersdirectory.gamersapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "level",
        uniqueConstraints = {
        @UniqueConstraint(name = "level_name_unique ", columnNames = "name")
        })
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    String name;

    @OneToMany(mappedBy = "level")
    private List<AccountGame> accountGames;
}
