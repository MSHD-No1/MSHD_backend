package com.earthquake.managementPlatform.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class FtpFileMethodTest {
    @Resource
    FtpFileMethod ftpFileMethod;

    @Test
    public void downloadFilesTest() throws Exception {
        FtpMethod ftpMethod = ftpFileMethod;
        ftpMethod.downloadFiles();

    }


}