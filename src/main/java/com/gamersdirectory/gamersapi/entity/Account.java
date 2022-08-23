package com.gamersdirectory.gamersapi.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private Long id;

    private String name;

    private String nickname;

    @ManyToOne// many accounts have one location
    @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE})
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany()
    @Cascade({ CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "account_interest",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    private List<Interest> interests = new ArrayList<>();

}
