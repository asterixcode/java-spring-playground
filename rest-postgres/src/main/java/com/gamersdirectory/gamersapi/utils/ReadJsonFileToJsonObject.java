package com.gamersdirectory.gamersapi.utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import java.io.InputStream;
import java.util.Scanner;

public class ReadJsonFileToJsonObject {

    @Value("classpath:${openapi.response.filepath}")
    Resource resourceFile;

    public JSONObject read() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("openapi/response.json");
        assert inputStream != null;
        String content = new Scanner(inputStream).useDelimiter("\\A").next();
        return new JSONObject(content);
    }
}
