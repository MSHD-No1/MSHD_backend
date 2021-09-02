package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.IrrigationDisaster;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.IrrigationDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class IrrigationDisasterResource {
    @Resource
    IrrigationDisasterMapper irrigationDisasterMapper;

    @GetMapping("/v1/irrigationDisaster")
    public GetVo<IrrigationDisaster> irrigationDisasterAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = irrigationDisasterMapper.getAllIrrigationDisaster().size();
        List<IrrigationDisaster> irrigationDisaster = irrigationDisasterMapper.getIrrigationDisasterByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, irrigationDisaster);
    }

    @GetMapping("/v1/irrigationDisaster/{time}")
    public GetVo<IrrigationDisaster> irrigationDisasterByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = irrigationDisasterMapper.getRecentIrrigationDisaster(timestamp).size();
        List<IrrigationDisaster> irrigationDisasters = irrigationDisasterMapper.getRecentIrrigationDisasterByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, irrigationDisasters);
    }

    @GetMapping("/v1/lastIrrigationDisasterStatistics")
    public GetVo<LifeLineStatistics> getLastIrrigationDisasterStatistics() {
        List<LifeLineStatistics> lifeLineStatistics = irrigationDisasterMapper.getIrrigationStatistics();
        return new GetVo<>(0, "获取数据成功！", lifeLineStatistics.size(), lifeLineStatistics);
    }

    @PutMapping("/v1/irrigationDisaster/{id}")
    public PostVo<IrrigationDisaster> editIrrigationDisaster(HttpServletRequest request, @PathVariable("id") String id) {
        IrrigationDisaster irrigationDisaster = new IrrigationDisaster();
        irrigationDisaster.setId(id);
        irrigationDisaster.setDate(request.getParameter("date"));
        irrigationDisaster.setLocation(request.getParameter("location"));
        irrigationDisaster.setType(request.getParameter("type"));
        irrigationDisaster.setGrade(request.getParameter("grade"));
        irrigationDisaster.setPicture(request.getParameter("picture"));
        irrigationDisaster.setNote(request.getParameter("note"));
        irrigationDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        irrigationDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        irrigationDisasterMapper.update(irrigationDisaster);
        return new PostVo(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/irrigationDisaster/{id}")
    public PostVo<IrrigationDisaster> delIrrigationDisaster(@PathVariable("id") String id) {
        irrigationDisasterMapper.deleteById(id);
        return new PostVo(0, "删除成功!", null);
    }

    @GetMapping("/v1/irrigationDisasterCopy/{time}")
    public List<IrrigationDisaster> irrigationDisasterCopy(@PathVariable("time") int time) {
        return irrigationDisasterMapper.getCopyIrrigationDisaster(time * 24);
    }
}
