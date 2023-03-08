package com.gamersdirectory.gamersapi.utils;

public enum ErrorMessageEnum {
    ACCOUNT_ID_NOT_FOUND("Account Id Not Found, Id: %s"),
    LEVEL_NOT_FOUND("Level Not Found, Level: %s"),
    GAME_NOT_FOUND("Game Not Found, Game: %s"),
    LOCATION_NOT_FOUND("Location Not Found, Location: %s");

    private final String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
