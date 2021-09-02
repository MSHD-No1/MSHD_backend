package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.TrafficDisaster;
import com.earthquake.managementPlatform.mapper.TrafficDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TrafficDisasterResource {
    @Resource
    TrafficDisasterMapper trafficDisasterMapper;

    @GetMapping("/v1/trafficDisaster")
    public GetVo<TrafficDisaster> trafficDisasterAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = trafficDisasterMapper.getAllTrafficDisaster().size();
        List<TrafficDisaster> trafficDisaster = trafficDisasterMapper.getTrafficDisasterByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, trafficDisaster);
    }

    @GetMapping("/v1/trafficDisaster/{time}")
    public GetVo<TrafficDisaster> trafficDisasterByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = trafficDisasterMapper.getRecentTrafficDisaster(timestamp).size();
        List<TrafficDisaster> trafficDisasters = trafficDisasterMapper.getRecentTrafficDisasterByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, trafficDisasters);
    }

    @GetMapping("/v1/lastTrafficDisasterStatistics")
    public GetVo<LifeLineStatistics> getLastTrafficDisasterStatistics() {
        List<LifeLineStatistics> lifeLineStatistics = trafficDisasterMapper.getTrafficStatistics();
        return new GetVo<>(0, "获取数据成功！", lifeLineStatistics.size(), lifeLineStatistics);
    }

    @PutMapping("/v1/trafficDisaster/{id}")
    public PostVo<TrafficDisaster> editTrafficDisaster(HttpServletRequest request, @PathVariable("id") String id) {
        TrafficDisaster trafficDisaster = new TrafficDisaster();
        trafficDisaster.setId(id);
        trafficDisaster.setDate(request.getParameter("date"));
        trafficDisaster.setLocation(request.getParameter("location"));
        trafficDisaster.setType(request.getParameter("type"));
        trafficDisaster.setGrade(request.getParameter("grade"));
        trafficDisaster.setPicture(request.getParameter("picture"));
        trafficDisaster.setNote(request.getParameter("note"));
        trafficDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        trafficDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        trafficDisasterMapper.update(trafficDisaster);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/trafficDisaster/{id}")
    public PostVo<TrafficDisaster> delTrafficDisaster(@PathVariable("id") String id) {
        trafficDisasterMapper.deleteById(id);
        return new PostVo(0, "删除成功!", null);
    }

    @GetMapping("/v1/trafficDisasterCopy/{time}")
    public List<TrafficDisaster> trafficDisasterCopy(@PathVariable("time") int time) {
        return trafficDisasterMapper.getCopyTrafficDisaster(time * 24);
    }
}
