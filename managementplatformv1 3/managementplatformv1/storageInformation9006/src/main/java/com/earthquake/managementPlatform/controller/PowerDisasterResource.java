package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.PowerDisaster;
import com.earthquake.managementPlatform.mapper.PowerDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class PowerDisasterResource {
    @Resource
    PowerDisasterMapper powerDisasterMapper;

    @GetMapping("/v1/powerDisaster")
    public GetVo<PowerDisaster> powerDisasterAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = powerDisasterMapper.getAllPowerDisaster().size();
        List<PowerDisaster> powerDisaster = powerDisasterMapper.getPowerDisasterByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, powerDisaster);
    }

    @GetMapping("/v1/powerDisaster/{time}")
    public GetVo<PowerDisaster> powerDisasterByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = powerDisasterMapper.getRecentPowerDisaster(timestamp).size();
        List<PowerDisaster> powerDisasters = powerDisasterMapper.getRecentPowerDisasterByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, powerDisasters);
    }

    @GetMapping("/v1/lastPowerDisasterStatistics")
    public GetVo<LifeLineStatistics> getLastPowerDisasterStatistics() {
        List<LifeLineStatistics> lifeLineStatistics = powerDisasterMapper.getPowerStatistics();
        return new GetVo<>(0, "获取数据成功！", lifeLineStatistics.size(), lifeLineStatistics);
    }

    @PutMapping("/v1/powerDisaster/{id}")
    public PostVo<PowerDisaster> editPowerDisaster(HttpServletRequest request, @PathVariable("id") String id) {
        PowerDisaster powerDisaster = new PowerDisaster();
        powerDisaster.setId(id);
        powerDisaster.setDate(request.getParameter("date"));
        powerDisaster.setLocation(request.getParameter("location"));
        powerDisaster.setType(request.getParameter("type"));
        powerDisaster.setGrade(request.getParameter("grade"));
        powerDisaster.setPicture(request.getParameter("picture"));
        powerDisaster.setNote(request.getParameter("note"));
        powerDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        powerDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        powerDisasterMapper.update(powerDisaster);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/powerDisaster/{id}")
    public PostVo<PowerDisaster> delPowerDisaster(@PathVariable("id") String id) {
        powerDisasterMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/powerDisasterCopy/{time}")
    public List<PowerDisaster> powerDisasterCopy(@PathVariable("time") int time) {
        return powerDisasterMapper.getCopyPowerDisaster(time * 24);
    }
}
