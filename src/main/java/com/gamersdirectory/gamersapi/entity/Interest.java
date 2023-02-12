package com.gamersdirectory.gamersapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // many interest have the same one game
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne // many interest have the same one level
    @JoinColumn(name = "level_id")
    private Level level;
}
