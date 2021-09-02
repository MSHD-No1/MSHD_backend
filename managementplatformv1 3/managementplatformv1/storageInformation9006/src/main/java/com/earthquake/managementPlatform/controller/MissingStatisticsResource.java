package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.MissingStatistics;
import com.earthquake.managementPlatform.entities.PersonStatistics;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.MissingStatisticsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MissingStatisticsResource {
    @Resource
    MissingStatisticsMapper missingStatisticsMapper;

    @GetMapping("/v1/missingStatistics")
    public GetVo<MissingStatistics> missingStatisticsAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = missingStatisticsMapper.getAllMissingStatistics().size();
        List<MissingStatistics> missingStatistics = missingStatisticsMapper.getMissingStatisticsByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, missingStatistics);
    }

    @GetMapping("/v1/missingStatistics/{time}")
    public GetVo<MissingStatistics> missingStatisticsByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = missingStatisticsMapper.getRecentMissingStatistics(timestamp).size();
        List<MissingStatistics> missingStatistics = missingStatisticsMapper.getRecentMissingStatisticsByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, missingStatistics);
    }

    @GetMapping("/v1/lastMissingStatisticsByTime")
    public GetVo<PersonStatistics> getLastMissingStatisticsByTime() {
        List<PersonStatistics> personStatistics = missingStatisticsMapper.getLastMissingStatisticsByTime();
        return new GetVo<>(0, "获取数据成功！", personStatistics.size(), personStatistics);
    }

    @PutMapping("/v1/missingStatistics/{id}")
    public PostVo<MissingStatistics> editMissingStatistics(HttpServletRequest request, @PathVariable("id") String id) {
        MissingStatistics missingStatistics = new MissingStatistics();
        missingStatistics.setId(id);
        missingStatistics.setDate(request.getParameter("date"));
        missingStatistics.setLocation(request.getParameter("location"));
        missingStatistics.setNumber(Integer.parseInt(request.getParameter("number")));
        missingStatistics.setReportingUnit(request.getParameter("reportingUnit"));
        missingStatistics.setEarthquakeId(request.getParameter("earthquakeId"));
        missingStatisticsMapper.update(missingStatistics);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/missingStatistics/{id}")
    public PostVo<MissingStatistics> delMissingStatistics(@PathVariable("id") String id) {
        missingStatisticsMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/missingStatisticsCopy/{time}")
    public List<MissingStatistics> missingStatisticsCopy(@PathVariable("time") int time) {
        return missingStatisticsMapper.getCopyMissingStatistics(time * 24);
    }


}
