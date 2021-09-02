package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GasDisaster;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.GasDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class GasDisasterResource {
    @Resource
    GasDisasterMapper gasDisasterMapper;

    @GetMapping("/v1/gasDisaster")
    public GetVo<GasDisaster> gasDisasterAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = gasDisasterMapper.getAllGasDisaster().size();
        List<GasDisaster> gasDisaster = gasDisasterMapper.getGasDisasterByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, gasDisaster);
    }

    @GetMapping("/v1/gasDisaster/{time}")
    public GetVo<GasDisaster> gasDisasterByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = gasDisasterMapper.getRecentGasDisaster(timestamp).size();
        List<GasDisaster> gasDisasters = gasDisasterMapper.getRecentGasDisasterByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, gasDisasters);
    }

    @GetMapping("/v1/lastGasDisasterStatistics")
    public GetVo<LifeLineStatistics> getLastGasDisasterStatistics() {
        List<LifeLineStatistics> lifeLineStatistics = gasDisasterMapper.getGasStatistics();
        return new GetVo<>(0, "获取数据成功！", lifeLineStatistics.size(), lifeLineStatistics);
    }

    @PutMapping("/v1/gasDisaster/{id}")
    public PostVo<GasDisaster> editGasDisaster(HttpServletRequest request, @PathVariable("id") String id) {
        GasDisaster gasDisaster = new GasDisaster();
        gasDisaster.setId(id);
        gasDisaster.setDate(request.getParameter("date"));
        gasDisaster.setLocation(request.getParameter("location"));
        gasDisaster.setType(request.getParameter("type"));
        gasDisaster.setGrade(request.getParameter("grade"));
        gasDisaster.setPicture(request.getParameter("picture"));
        gasDisaster.setNote(request.getParameter("note"));
        gasDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        gasDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        gasDisasterMapper.update(gasDisaster);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/gasDisaster/{id}")
    public PostVo<GasDisaster> delGasDisaster(@PathVariable("id") String id) {
        gasDisasterMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/gasDisasterCopy/{time}")
    public List<GasDisaster> gasDisasterCopy(@PathVariable("time") int time) {
        return gasDisasterMapper.getCopyGasDisaster(time * 24);
    }
}
