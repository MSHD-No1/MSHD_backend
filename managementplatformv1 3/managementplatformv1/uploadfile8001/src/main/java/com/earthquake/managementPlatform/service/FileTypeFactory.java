package com.earthquake.managementPlatform.service;

import org.springframework.stereotype.Component;

@Component
public class FileTypeFactory {
    public DisasterFile createFile(String filePath) {
        DisasterFile disasterFile = null;
        String[] strArray = filePath.split("\\.");
        int suffixIndex = strArray.length - 1;

        switch (strArray[suffixIndex]) {
            case "xml":
                disasterFile = new XmlFile(filePath);
                break;
            case "json":
                disasterFile = new JsonFile(filePath);
                break;
            case "xlsx":
                disasterFile = new ExcelFile(filePath);
                break;
        }
        return disasterFile;
    }
}
