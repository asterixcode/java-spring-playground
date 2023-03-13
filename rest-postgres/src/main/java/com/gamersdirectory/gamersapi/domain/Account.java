package com.gamersdirectory.gamersapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "location_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_location_id_fk"))
    private Location location;

    @OneToMany(mappedBy = "account")
    private List<AccountGame> accountGames = new ArrayList<>();

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate = new Date();
}
