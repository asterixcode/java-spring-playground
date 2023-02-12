package com.gamersdirectory.gamersapi.dto;

import com.gamersdirectory.gamersapi.entity.Account;
import com.gamersdirectory.gamersapi.entity.Interest;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private String name;
    private String nickname;
    private String location;
    private List<InterestDTO> interests;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.nickname = account.getNickname();
        this.location = account.getLocation().getName();
        this.interests = mapInterests(account);
    }

    private List<InterestDTO> mapInterests(Account account) {

        if (account.getInterests().isEmpty()) {
            return Collections.emptyList();
        }

        List<InterestDTO> list = new ArrayList<>();

        List<Interest> interestList = account.getInterests()
                .stream()
                .toList();

        for (Interest interest : interestList) {
            InterestDTO interestDTO = new InterestDTO();
            interestDTO.setGame(interest.getGame().getName());
            interestDTO.setLevel(interest.getLevel().getName());
            list.add(interestDTO);
        }

        return list;
    }
}
