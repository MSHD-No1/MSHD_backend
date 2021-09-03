package com.earthquake.managementPlatform.service;

import org.json.JSONArray;

import java.io.IOException;

public interface FileBehavior {
    JSONArray transferToJson(String filePath) throws IOException;
}
