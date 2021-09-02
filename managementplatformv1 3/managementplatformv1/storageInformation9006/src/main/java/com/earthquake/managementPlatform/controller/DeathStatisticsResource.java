package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.DeathStatistics;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PersonStatistics;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.DeathStatisticsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DeathStatisticsResource {
    @Resource
    DeathStatisticsMapper deathStatisticsMapper;

    @GetMapping("/v1/deathStatistics")
    public GetVo<DeathStatistics> deathStatisticsAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = deathStatisticsMapper.getAllDeathStatistics().size();
        List<DeathStatistics> deathStatistics = deathStatisticsMapper.getDeathStatisticsByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, deathStatistics);
    }

    @GetMapping("/v1/deathStatistics/{time}")
    public GetVo<DeathStatistics> deathStatisticsByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = deathStatisticsMapper.getRecentDeathStatistics(timestamp).size();
        List<DeathStatistics> deathStatistics = deathStatisticsMapper.getRecentDeathStatisticsByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, deathStatistics);
    }

    @GetMapping("/v1/lastDeathStatisticsByTime")
    public GetVo<PersonStatistics> provinceAid() {
        List<PersonStatistics> personStatistics = deathStatisticsMapper.getLastDeathStatisticsByTime();
        return new GetVo<>(0, "获取数据成功！", personStatistics.size(), personStatistics);
    }

    @PutMapping("/v1/deathStatistics/{id}")
    public PostVo<DeathStatistics> editDeathStatistics(HttpServletRequest request, @PathVariable("id") String id) {
        DeathStatistics deathStatistics = new DeathStatistics();
        deathStatistics.setId(id);
        deathStatistics.setDate(request.getParameter("date"));
        deathStatistics.setLocation(request.getParameter("location"));
        deathStatistics.setNumber(Integer.parseInt(request.getParameter("number")));
        deathStatistics.setReportingUnit(request.getParameter("reportingUnit"));
        deathStatistics.setEarthquakeId(request.getParameter("earthquakeId"));
        deathStatisticsMapper.update(deathStatistics);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/deathStatistics/{id}")
    public PostVo<DeathStatistics> delDeathStatistics(@PathVariable("id") String id) {
        deathStatisticsMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/deathStatisticsCopy/{time}")
    public List<DeathStatistics> deathStatisticsCopy(@PathVariable("time") int time) {
        return deathStatisticsMapper.getCopyDeathStatistics(time * 24);
    }
}
