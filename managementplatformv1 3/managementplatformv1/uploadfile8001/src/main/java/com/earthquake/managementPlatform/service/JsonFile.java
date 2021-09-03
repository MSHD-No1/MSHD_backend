package com.earthquake.managementPlatform.service;

public class JsonFile extends DisasterFile{
    public JsonFile() {
        this.fileBehavior = new JsonBehavior();
    }

    public JsonFile(String filePath){
        this.fileBehavior = new JsonBehavior();
        this.filePath = filePath;
    }
}
