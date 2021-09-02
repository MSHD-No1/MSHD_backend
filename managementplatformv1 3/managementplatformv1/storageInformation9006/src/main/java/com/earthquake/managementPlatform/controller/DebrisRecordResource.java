package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.DebrisRecord;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.mapper.DebrisRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DebrisRecordResource {
    @Resource
    DebrisRecordMapper debrisRecordMapper;

    @GetMapping("/v1/debrisRecord")
    public GetVo<DebrisRecord> debrisRecordAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = debrisRecordMapper.getAllDebrisRecord().size();
        List<DebrisRecord> debrisRecord = debrisRecordMapper.getDebrisRecordByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, debrisRecord);
    }

    @GetMapping("/v1/debrisRecord/{time}")
    public GetVo<DebrisRecord> debrisRecordByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = debrisRecordMapper.getRecentDebrisRecord(timestamp).size();
        List<DebrisRecord> debrisRecords = debrisRecordMapper.getRecentDebrisRecordByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, debrisRecords);
    }

    @GetMapping("/v1/lastDebrisRecordStatistics")
    public GetVo<SecondaryDisasterStatistics> getLastDebrisRecordStatistics() {
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = debrisRecordMapper.getDebrisStatistics();
        return new GetVo<>(0, "获取数据成功！", secondaryDisasterStatistics.size(), secondaryDisasterStatistics);
    }

    @PutMapping("/v1/debrisRecord/{id}")
    public PostVo<DebrisRecord> editDebrisRecord(HttpServletRequest request, @PathVariable("id") String id) {
        DebrisRecord debrisRecord = new DebrisRecord();
        debrisRecord.setId(id);
        debrisRecord.setDate(request.getParameter("date"));
        debrisRecord.setLocation(request.getParameter("location"));
        debrisRecord.setType(request.getParameter("type"));
        debrisRecord.setStatus(request.getParameter("status"));
        debrisRecord.setPicture(request.getParameter("picture"));
        debrisRecord.setNote(request.getParameter("note"));
        debrisRecord.setReportingUnit(request.getParameter("reportingUnit"));
        debrisRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        debrisRecordMapper.update(debrisRecord);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/debrisRecord/{id}")
    public PostVo<DebrisRecord> delDebrisRecord(@PathVariable("id") String id) {
        debrisRecordMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/debrisRecordCopy/{time}")
    public List<DebrisRecord> debrisRecordCopy(@PathVariable("time") int time) {
        return debrisRecordMapper.getCopyDebrisRecord(time * 24);
    }
}
