package com.gamersdirectory.gamersapi.utils;

import com.gamersdirectory.gamersapi.domain.Game;
import com.gamersdirectory.gamersapi.domain.Level;
import com.gamersdirectory.gamersapi.dto.GameDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapper extends ModelMapper {

    public CustomModelMapper() {
        super();

        getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        addTypeMapForGameToGameDTO();

    }

    private void addTypeMapForGameToGameDTO() {
        TypeMap<Game, GameDTO> gameToGameDTOTypeMap = createTypeMap(Game.class, GameDTO.class);
        Converter<Level, String> levelToStringConverter = context -> context.getSource().getName();
        gameToGameDTOTypeMap.addMappings(mapper -> mapper.using(levelToStringConverter).map(Game::getLevel, GameDTO::setLevel));
    }


}
