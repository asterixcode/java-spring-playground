package com.gamersdirectory.gamersapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account",
        uniqueConstraints = {
        @UniqueConstraint(name = "account_email_unique ", columnNames = "email"),
        @UniqueConstraint(name = "account_nickname_unique ", columnNames = "nickname")
        })
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(
            name = "location_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "account_location_id_fk"))
    private Location location;

    @OneToMany(
            mappedBy = "account",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<AccountGame> accountGames = new ArrayList<>();

    @Column(name = "created_at",
            updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void addAccountGame(AccountGame accountGame) {
        if (!accountGames.contains(accountGame)) {
            accountGames.add(accountGame);
            accountGame.setAccount(this);
        }
    }

    public void removeAccountGame(AccountGame accountGame) {
        accountGames.remove(accountGame);
        accountGame.setAccount(null);
    }
}
