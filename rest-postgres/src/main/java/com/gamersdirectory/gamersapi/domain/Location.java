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
@Table(name = "location",
        uniqueConstraints = {
        @UniqueConstraint(name = "location_continent_unique", columnNames = "continent")
        })
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "continent", nullable = false)
    private String continent;

    @OneToMany(mappedBy = "location")
    private List<Account> accounts = new ArrayList<>();
}
