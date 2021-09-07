package com.earthquake.managementPlatform.service;

public class ExcelFile extends DisasterFile {
    public ExcelFile() {
        this.fileBehavior = new ExcelBehavior();
    }

    public ExcelFile(String filePath) {
        this.fileBehavior = new ExcelBehavior();
        this.filePath = filePath;
    }
}
