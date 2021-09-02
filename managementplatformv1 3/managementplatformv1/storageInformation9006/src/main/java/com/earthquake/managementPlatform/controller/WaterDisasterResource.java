package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.WaterDisaster;
import com.earthquake.managementPlatform.mapper.WaterDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class WaterDisasterResource {
    @Resource
    WaterDisasterMapper waterDisasterMapper;

    @GetMapping("/v1/waterDisaster")
    public GetVo<WaterDisaster> waterDisasterAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = waterDisasterMapper.getAllWaterDisaster().size();
        List<WaterDisaster> waterDisaster = waterDisasterMapper.getWaterDisasterByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, waterDisaster);
    }

    @GetMapping("/v1/waterDisaster/{time}")
    public GetVo<WaterDisaster> waterDisasterByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = waterDisasterMapper.getRecentWaterDisaster(timestamp).size();
        List<WaterDisaster> waterDisasters = waterDisasterMapper.getRecentWaterDisasterByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, waterDisasters);
    }

    @GetMapping("/v1/lastWaterDisasterStatistics")
    public GetVo<LifeLineStatistics> getLastWaterDisasterStatistics() {
        List<LifeLineStatistics> lifeLineStatistics = waterDisasterMapper.getWaterStatistics();
        return new GetVo<>(0, "获取数据成功！", lifeLineStatistics.size(), lifeLineStatistics);
    }

    @PutMapping("/v1/waterDisaster/{id}")
    public PostVo<WaterDisaster> editWaterDisaster(HttpServletRequest request, @PathVariable("id") String id) {
        WaterDisaster waterDisaster = new WaterDisaster();
        waterDisaster.setId(id);
        waterDisaster.setDate(request.getParameter("date"));
        waterDisaster.setLocation(request.getParameter("location"));
        waterDisaster.setType(request.getParameter("type"));
        waterDisaster.setGrade(request.getParameter("grade"));
        waterDisaster.setPicture(request.getParameter("picture"));
        waterDisaster.setNote(request.getParameter("note"));
        waterDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        waterDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        waterDisasterMapper.update(waterDisaster);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/waterDisaster/{id}")
    public PostVo<WaterDisaster> delWaterDisaster(@PathVariable("id") String id) {
        waterDisasterMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/waterDisasterCopy/{time}")
    public List<WaterDisaster> waterDisasterCopy(@PathVariable("time") int time) {
        return waterDisasterMapper.getCopyWaterDisaster(time * 24);
    }
}
