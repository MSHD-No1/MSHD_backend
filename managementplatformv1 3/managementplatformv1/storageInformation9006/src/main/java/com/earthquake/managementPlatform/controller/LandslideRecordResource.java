package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.LandslideRecord;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.mapper.LandslideRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LandslideRecordResource {
    @Resource
    LandslideRecordMapper landslideRecordMapper;

    @GetMapping("/v1/landslideRecord")
    public GetVo<LandslideRecord> landslideRecordAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = landslideRecordMapper.getAllLandslideRecord().size();
        List<LandslideRecord> landslideRecord = landslideRecordMapper.getLandslideRecordByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, landslideRecord);
    }

    @GetMapping("/v1/landslideRecord/{time}")
    public GetVo<LandslideRecord> landslideRecordByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = landslideRecordMapper.getRecentLandslideRecord(timestamp).size();
        List<LandslideRecord> landslideRecords = landslideRecordMapper.getRecentLandslideRecordByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, landslideRecords);
    }

    @GetMapping("/v1/lastLandslideRecordStatistics")
    public GetVo<SecondaryDisasterStatistics> getLastLandslideRecordStatistics() {
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = landslideRecordMapper.getLandslideStatistics();
        return new GetVo<>(0, "获取数据成功！", secondaryDisasterStatistics.size(), secondaryDisasterStatistics);
    }

    @PutMapping("/v1/landslideRecord/{id}")
    public PostVo<LandslideRecord> editLandslideRecord(HttpServletRequest request, @PathVariable("id") String id) {
        LandslideRecord landslideRecord = new LandslideRecord();
        landslideRecord.setId(id);
        landslideRecord.setDate(request.getParameter("date"));
        landslideRecord.setLocation(request.getParameter("location"));
        landslideRecord.setType(request.getParameter("type"));
        landslideRecord.setStatus(request.getParameter("status"));
        landslideRecord.setPicture(request.getParameter("picture"));
        landslideRecord.setNote(request.getParameter("note"));
        landslideRecord.setReportingUnit(request.getParameter("reportingUnit"));
        landslideRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        landslideRecordMapper.update(landslideRecord);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/landslideRecord/{id}")
    public PostVo<LandslideRecord> delLandslideRecord(@PathVariable("id") String id) {
        landslideRecordMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/landslideRecordCopy/{time}")
    public List<LandslideRecord> landslideRecordCopy(@PathVariable("time") int time) {
        return landslideRecordMapper.getCopyLandslideRecord(time * 24);
    }
}
