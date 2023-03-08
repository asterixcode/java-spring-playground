package com.gamersdirectory.gamersapi.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameRequest {

    @JsonProperty("name")
    private String name;
}
