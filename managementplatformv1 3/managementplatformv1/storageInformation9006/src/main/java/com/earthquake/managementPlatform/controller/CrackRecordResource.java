package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.CrackRecord;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.mapper.CrackRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CrackRecordResource {
    @Resource
    CrackRecordMapper crackRecordMapper;

    @GetMapping("/v1/crackRecord")
    public GetVo<CrackRecord> crackRecordAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = crackRecordMapper.getAllCrackRecord().size();
        List<CrackRecord> crackRecord = crackRecordMapper.getCrackRecordByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, crackRecord);
    }

    @GetMapping("/v1/crackRecord/{time}")
    public GetVo<CrackRecord> crackRecordByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = crackRecordMapper.getRecentCrackRecord(timestamp).size();
        List<CrackRecord> crackRecords = crackRecordMapper.getRecentCrackRecordByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, crackRecords);
    }

    @GetMapping("/v1/lastCrackRecordStatistics")
    public GetVo<SecondaryDisasterStatistics> getLastCrackRecordStatistics() {
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = crackRecordMapper.getCrackStatistics();
        return new GetVo<>(0, "获取数据成功！", secondaryDisasterStatistics.size(), secondaryDisasterStatistics);
    }

    @PutMapping("/v1/crackRecord/{id}")
    public PostVo<CrackRecord> editCrackRecord(HttpServletRequest request, @PathVariable("id") String id) {
        CrackRecord crackRecord = new CrackRecord();
        crackRecord.setId(id);
        crackRecord.setDate(request.getParameter("date"));
        crackRecord.setLocation(request.getParameter("location"));
        crackRecord.setType(request.getParameter("type"));
        crackRecord.setStatus(request.getParameter("status"));
        crackRecord.setPicture(request.getParameter("picture"));
        crackRecord.setNote(request.getParameter("note"));
        crackRecord.setReportingUnit(request.getParameter("reportingUnit"));
        crackRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        crackRecordMapper.update(crackRecord);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/crackRecord/{id}")
    public PostVo<CrackRecord> delCrackRecord(@PathVariable("id") String id) {
        crackRecordMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/crackRecordCopy/{time}")
    public List<CrackRecord> crackRecordCopy(@PathVariable("time") int time) {
        return crackRecordMapper.getCopyCrackRecord(time * 24);
    }
}
