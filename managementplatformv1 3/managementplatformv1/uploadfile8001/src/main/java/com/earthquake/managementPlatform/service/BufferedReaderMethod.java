package com.earthquake.managementPlatform.service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;


public class BufferedReaderMethod {
    public JSONObject BufferedReaderToJson(BufferedReader bufferedReader) throws IOException {
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = bufferedReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        return new JSONObject(responseStrBuilder.toString());
    }
}
