package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.DisasterPrediction;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.DisasterPredictionMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DisasterPredictionResource {
    @Resource
    DisasterPredictionMapper disasterPredictionMapper;

    @GetMapping("/v1/disasterPrediction")
    public GetVo<DisasterPrediction> disasterPredictionAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = disasterPredictionMapper.getAllDisasterPrediction().size();
        List<DisasterPrediction> disasterPrediction = disasterPredictionMapper.getDisasterPredictionByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, disasterPrediction);
    }

    @GetMapping("/v1/disasterPrediction/{time}")
    public GetVo<DisasterPrediction> disasterPredictionByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = disasterPredictionMapper.getRecentDisasterPrediction(timestamp).size();
        List<DisasterPrediction> disasterPredictions = disasterPredictionMapper.getRecentDisasterPredictionByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, disasterPredictions);

    }

    @PutMapping("/v1/disasterPrediction/{D_ID}/{S_ID}")
    public PostVo<DisasterPrediction> editDisasterPrediction(HttpServletRequest request, @PathVariable("D_ID") String D_ID, @PathVariable("S_ID") String S_ID) {
        DisasterPrediction disasterPrediction = new DisasterPrediction();
        disasterPrediction.setD_ID(D_ID);
        disasterPrediction.setS_ID(S_ID);
        disasterPrediction.setDate(request.getParameter("date"));
        disasterPrediction.setGrade(request.getParameter("grade"));
        disasterPrediction.setIntensity(request.getParameter("intensity"));
        disasterPrediction.setType(request.getParameter("type"));
        disasterPrediction.setPicture(request.getParameter("picture"));
        disasterPredictionMapper.update(disasterPrediction);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/disasterPrediction/{D_ID}/{S_ID}")
    public PostVo<DisasterPrediction> delDisasterPrediction(@PathVariable("D_ID") String D_ID, @PathVariable("S_ID") String S_ID) {
        disasterPredictionMapper.deleteById(D_ID, S_ID);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/disasterPredictionCopy/{time}")
    public List<DisasterPrediction> disasterPredictionCopy(@PathVariable("time") int time) {
        return disasterPredictionMapper.getCopyDisasterPrediction(time * 24);
    }
}
