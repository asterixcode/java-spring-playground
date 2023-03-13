package com.gamersdirectory.gamersapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "AccountGame")
@Table(name = "account_game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountGame {

    @EmbeddedId
    private AccountGameId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(
            name = "account_id",
            foreignKey = @ForeignKey(name = "account_game_account_id_fk")
    )
    private Account account;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(
            name = "game_id",
            foreignKey = @ForeignKey(name = "account_game_game_id_fk")
    )
    private Game game;

    @ManyToOne
    @MapsId("levelId")
    @JoinColumn(
            name = "level_id",
            foreignKey = @ForeignKey(name = "account_game_level_id_fk")
    )
    private Level level;

    @Column(name = "created_at",
            updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    public AccountGame(Account account, Game game, Level level) {
        this.account = account;
        this.game = game;
        this.level = level;
    }
}
