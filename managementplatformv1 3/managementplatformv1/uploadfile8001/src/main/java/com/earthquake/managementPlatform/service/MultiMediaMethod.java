package com.earthquake.managementPlatform.service;

import org.springframework.web.multipart.MultipartFile;

public interface MultiMediaMethod {
    String uploadMultiMedia(MultipartFile uploadFile);
}
