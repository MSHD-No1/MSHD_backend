package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.KarstRecord;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.mapper.KarstRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class KarstRecordResource {
    @Resource
    KarstRecordMapper karstRecordMapper;

    @GetMapping("/v1/karstRecord")
    public GetVo<KarstRecord> karstRecordAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = karstRecordMapper.getAllKarstRecord().size();
        List<KarstRecord> karstRecord = karstRecordMapper.getKarstRecordByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, karstRecord);
    }

    @GetMapping("/v1/karstRecord/{time}")
    public GetVo<KarstRecord> karstRecordByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = karstRecordMapper.getRecentKarstRecord(timestamp).size();
        List<KarstRecord> karstRecords = karstRecordMapper.getRecentKarstRecordByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, karstRecords);
    }

    @GetMapping("/v1/lastKarstRecordStatistics")
    public GetVo<SecondaryDisasterStatistics> getLastKarstRecordStatistics() {
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = karstRecordMapper.getKarstStatistics();
        return new GetVo<>(0, "获取数据成功！", secondaryDisasterStatistics.size(), secondaryDisasterStatistics);
    }

    @PutMapping("/v1/karstRecord/{id}")
    public PostVo<KarstRecord> editKarstRecord(HttpServletRequest request, @PathVariable("id") String id) {
        KarstRecord karstRecord = new KarstRecord();
        karstRecord.setId(id);
        karstRecord.setDate(request.getParameter("date"));
        karstRecord.setLocation(request.getParameter("location"));
        karstRecord.setType(request.getParameter("type"));
        karstRecord.setStatus(request.getParameter("status"));
        karstRecord.setPicture(request.getParameter("picture"));
        karstRecord.setNote(request.getParameter("note"));
        karstRecord.setReportingUnit(request.getParameter("reportingUnit"));
        karstRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        karstRecordMapper.update(karstRecord);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/karstRecord/{id}")
    public PostVo<KarstRecord> delKarstRecord(@PathVariable("id") String id) {
        karstRecordMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/karstRecordCopy/{time}")
    public List<KarstRecord> karstRecordCopy(@PathVariable("time") int time) {
        return karstRecordMapper.getCopyKarstRecord(time * 24);
    }
}
