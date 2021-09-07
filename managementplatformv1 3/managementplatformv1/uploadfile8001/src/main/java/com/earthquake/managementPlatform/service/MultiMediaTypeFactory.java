package com.earthquake.managementPlatform.service;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MultiMediaTypeFactory {
    @Resource
    PicMethod picMethod;

    public MultiMediaMethod createMultiMedia(String filePath) {
        MultiMediaMethod multiMediaMethod = null;
        String[] strArray = filePath.split("\\.");
        int suffixIndex = strArray.length - 1;

        switch (strArray[suffixIndex]) {
            case "jpg":
            case "png":
            case "jpeg":
                multiMediaMethod = picMethod;
                break;
        }
        return multiMediaMethod;
    }
}
