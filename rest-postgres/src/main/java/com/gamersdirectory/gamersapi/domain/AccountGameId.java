package com.gamersdirectory.gamersapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AccountGameId implements Serializable {

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "level_id")
    private Long levelId;
}
