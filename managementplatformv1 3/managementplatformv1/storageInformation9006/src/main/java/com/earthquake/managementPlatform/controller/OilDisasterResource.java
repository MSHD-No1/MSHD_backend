package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.OilDisaster;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.OilDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OilDisasterResource {
    @Resource
    OilDisasterMapper oilDisasterMapper;

    @GetMapping("/v1/oilDisaster")
    public GetVo<OilDisaster> oilDisasterAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = oilDisasterMapper.getAllOilDisaster().size();
        List<OilDisaster> oilDisaster = oilDisasterMapper.getOilDisasterByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, oilDisaster);
    }

    @GetMapping("/v1/oilDisaster/{time}")
    public GetVo<OilDisaster> oilDisasterByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = oilDisasterMapper.getRecentOilDisaster(timestamp).size();
        List<OilDisaster> oilDisasters = oilDisasterMapper.getRecentOilDisasterByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, oilDisasters);
    }

    @GetMapping("/v1/lastOilDisasterStatistics")
    public GetVo<LifeLineStatistics> getLastOilDisasterStatistics() {
        List<LifeLineStatistics> lifeLineStatistics = oilDisasterMapper.getOilStatistics();
        return new GetVo<>(0, "获取数据成功！", lifeLineStatistics.size(), lifeLineStatistics);
    }

    @PutMapping("/v1/oilDisaster/{id}")
    public PostVo<OilDisaster> editOilDisaster(HttpServletRequest request, @PathVariable("id") String id) {
        OilDisaster oilDisaster = new OilDisaster();
        oilDisaster.setId(id);
        oilDisaster.setDate(request.getParameter("date"));
        oilDisaster.setLocation(request.getParameter("location"));
        oilDisaster.setType(request.getParameter("type"));
        oilDisaster.setGrade(request.getParameter("grade"));
        oilDisaster.setPicture(request.getParameter("picture"));
        oilDisaster.setNote(request.getParameter("note"));
        oilDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        oilDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        oilDisasterMapper.update(oilDisaster);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/oilDisaster/{id}")
    public PostVo<OilDisaster> delOilDisaster(@PathVariable("id") String id) {
        oilDisasterMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/oilDisasterCopy/{time}")
    public List<OilDisaster> oilDisasterCopy(@PathVariable("time") int time) {
        return oilDisasterMapper.getCopyOilDisaster(time * 24);
    }
}
