package com.gamersdirectory.gamersapi.data.response;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse {

    private int statusCode;
    private String reasonPhrase;
    private String message;

}
