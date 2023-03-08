package com.gamersdirectory.gamersapi.utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJsonFileToJsonObject {

    public JSONObject read() throws IOException {
        String file = "rest-postgres/src/main/resources/openapi/response.json";
        String content = new String(Files.readAllBytes(Paths.get(file)));
        return new JSONObject(content);
    }
}
