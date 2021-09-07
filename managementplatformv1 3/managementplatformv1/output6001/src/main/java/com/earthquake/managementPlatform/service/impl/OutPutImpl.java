package com.earthquake.managementPlatform.service.impl;

import com.earthquake.managementPlatform.service.FTPOutPutMethod;
import com.earthquake.managementPlatform.service.OutPut;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class OutPutImpl extends FTPOutPutMethod implements OutPut {
    @Override
    public boolean outPut(String categoryId, String code) throws IOException {
        return outPutForCode(categoryId, code);
    }

    private boolean outPutForCode(String categoryId, String code) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(categoryId + ".txt"));
        out.write(code);
        out.close();
        return true;
    }
}
