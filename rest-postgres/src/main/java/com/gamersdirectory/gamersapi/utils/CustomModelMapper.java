package com.gamersdirectory.gamersapi.utils;

import com.gamersdirectory.gamersapi.domain.Account;
import com.gamersdirectory.gamersapi.domain.AccountGame;
import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.dto.AccountGameDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class CustomModelMapper extends ModelMapper {

    public CustomModelMapper() {
        super();

        getConfiguration()
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true);

        this.createTypeMap(Account.class, AccountDTO.class)
                .addMapping(Account::getLocation, AccountDTO::setLocation)
                .addMapping(Account::getAccountGames, AccountDTO::setGames);

        this.createTypeMap(AccountGame.class, AccountGameDTO.class)
                .addMappings(mapper -> mapper.map(src -> src.getGame().getName(), AccountGameDTO::setName))
                .addMappings(mapper -> mapper.map(src -> src.getLevel().getName(), AccountGameDTO::setLevel));
    }

}
