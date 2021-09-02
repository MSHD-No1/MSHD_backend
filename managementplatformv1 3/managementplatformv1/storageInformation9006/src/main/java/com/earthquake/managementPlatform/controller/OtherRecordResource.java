package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.OtherRecord;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.mapper.OtherRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OtherRecordResource {
    @Resource
    OtherRecordMapper otherRecordMapper;

    @GetMapping("/v1/otherRecord")
    public GetVo<OtherRecord> otherRecordAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = otherRecordMapper.getAllOtherRecord().size();
        List<OtherRecord> otherRecord = otherRecordMapper.getOtherRecordByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, otherRecord);
    }

    @GetMapping("/v1/otherRecord/{time}")
    public GetVo<OtherRecord> otherRecordByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = otherRecordMapper.getRecentOtherRecord(timestamp).size();
        List<OtherRecord> otherRecords = otherRecordMapper.getRecentOtherRecordByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, otherRecords);
    }

    @GetMapping("/v1/lastOtherRecordStatistics")
    public GetVo<SecondaryDisasterStatistics> getLastOtherRecordStatistics() {
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = otherRecordMapper.getOtherStatistics();
        return new GetVo<>(0, "获取数据成功！", secondaryDisasterStatistics.size(), secondaryDisasterStatistics);
    }

    @PutMapping("/v1/otherRecord/{id}")
    public PostVo<OtherRecord> editOtherRecord(HttpServletRequest request, @PathVariable("id") String id) {
        OtherRecord otherRecord = new OtherRecord();
        otherRecord.setId(id);
        otherRecord.setDate(request.getParameter("date"));
        otherRecord.setLocation(request.getParameter("location"));
        otherRecord.setType(request.getParameter("type"));
        otherRecord.setStatus(request.getParameter("status"));
        otherRecord.setPicture(request.getParameter("picture"));
        otherRecord.setNote(request.getParameter("note"));
        otherRecord.setReportingUnit(request.getParameter("reportingUnit"));
        otherRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        otherRecordMapper.update(otherRecord);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/otherRecord/{id}")
    public PostVo<OtherRecord> delOtherRecord(@PathVariable("id") String id) {
        otherRecordMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/otherRecordCopy/{time}")
    public List<OtherRecord> otherRecordCopy(@PathVariable("time") int time) {
        return otherRecordMapper.getCopyOtherRecord(time * 24);
    }


}
