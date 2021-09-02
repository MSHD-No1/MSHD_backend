package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.entities.SettlementRecord;
import com.earthquake.managementPlatform.mapper.SettlementRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SettlementRecordResource {
    @Resource
    SettlementRecordMapper settlementRecordMapper;

    @GetMapping("/v1/settlementRecord")
    public GetVo<SettlementRecord> settlementRecordAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = settlementRecordMapper.getAllSettlementRecord().size();
        List<SettlementRecord> settlementRecords = settlementRecordMapper.getSettlementRecordByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, settlementRecords);
    }

    @GetMapping("/v1/settlementRecord/{time}")
    public GetVo<SettlementRecord> settlementRecordByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = settlementRecordMapper.getRecentSettlementRecord(timestamp).size();
        List<SettlementRecord> settlementRecords = settlementRecordMapper.getRecentSettlementRecordByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, settlementRecords);
    }

    @GetMapping("/v1/lastSettlementRecordStatistics")
    public GetVo<SecondaryDisasterStatistics> getLastSettlementRecordStatistics() {
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = settlementRecordMapper.getSettlementStatistics();
        return new GetVo<>(0, "获取数据成功！", secondaryDisasterStatistics.size(), secondaryDisasterStatistics);
    }

    @PutMapping("/v1/settlementRecord/{id}")
    public PostVo<SettlementRecord> editSettlementRecord(HttpServletRequest request, @PathVariable("id") String id) {
        SettlementRecord settlementRecord = new SettlementRecord();
        settlementRecord.setId(id);
        settlementRecord.setDate(request.getParameter("date"));
        settlementRecord.setLocation(request.getParameter("location"));
        settlementRecord.setType(request.getParameter("type"));
        settlementRecord.setStatus(request.getParameter("status"));
        settlementRecord.setPicture(request.getParameter("picture"));
        settlementRecord.setNote(request.getParameter("note"));
        settlementRecord.setReportingUnit(request.getParameter("reportingUnit"));
        settlementRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        settlementRecordMapper.update(settlementRecord);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/settlementRecord/{id}")
    public PostVo<SettlementRecord> delSettlementRecord(@PathVariable("id") String id) {
        settlementRecordMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/settlementRecordCopy/{time}")
    public List<SettlementRecord> settlementRecordCopy(@PathVariable("time") int time) {
        return settlementRecordMapper.getCopySettlementRecord(time * 24);
    }
}
