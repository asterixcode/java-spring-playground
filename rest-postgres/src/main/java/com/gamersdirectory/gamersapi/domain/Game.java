package com.gamersdirectory.gamersapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game",
        uniqueConstraints = {
        @UniqueConstraint(name = "game_name_unique", columnNames = "name")
        })
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @OneToMany(
            mappedBy = "game",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<AccountGame> accountGames = new  ArrayList<>();

    public void addAccountGame(AccountGame accountGame) {
        if (!accountGames.contains(accountGame)) {
            accountGames.add(accountGame);
        }
    }

    public void removeAccountGame(AccountGame accountGame) {
        accountGames.remove(accountGame);
    }
}
