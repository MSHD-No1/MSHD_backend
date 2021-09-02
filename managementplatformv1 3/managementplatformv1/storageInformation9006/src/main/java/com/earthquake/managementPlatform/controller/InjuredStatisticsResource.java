package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.InjuredStatistics;
import com.earthquake.managementPlatform.entities.PersonStatistics;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.InjuredStatisticsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class InjuredStatisticsResource {
    @Resource
    InjuredStatisticsMapper injuredStatisticsMapper;

    @GetMapping("/v1/injuredStatistics")
    public GetVo<InjuredStatistics> injuredStatisticsAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = injuredStatisticsMapper.getAllInjuredStatistics().size();
        List<InjuredStatistics> injuredStatistics = injuredStatisticsMapper.getInjuredStatisticsByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, injuredStatistics);
    }

    @GetMapping("/v1/injuredStatistics/{time}")
    public GetVo<InjuredStatistics> injuredStatisticsByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = injuredStatisticsMapper.getRecentInjuredStatistics(timestamp).size();
        List<InjuredStatistics> injuredStatistics = injuredStatisticsMapper.getRecentInjuredStatisticsByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, injuredStatistics);
    }

    @GetMapping("/v1/lastInjuredStatisticsByTime")
    public GetVo<PersonStatistics> getLastInjuredStatisticsByTime() {
        List<PersonStatistics> personStatistics = injuredStatisticsMapper.getLastInjuredStatisticsByTime();
        return new GetVo<>(0, "获取数据成功！", personStatistics.size(), personStatistics);
    }

    @PutMapping("/v1/injuredStatistics/{id}")
    public PostVo<InjuredStatistics> editInjuredStatistics(HttpServletRequest request, @PathVariable("id") String id) {
        InjuredStatistics injuredStatistics = new InjuredStatistics();
        injuredStatistics.setId(id);
        injuredStatistics.setDate(request.getParameter("date"));
        injuredStatistics.setLocation(request.getParameter("location"));
        injuredStatistics.setNumber(Integer.parseInt(request.getParameter("number")));
        injuredStatistics.setReportingUnit(request.getParameter("reportingUnit"));
        injuredStatistics.setEarthquakeId(request.getParameter("earthquakeId"));
        injuredStatisticsMapper.update(injuredStatistics);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/injuredStatistics/{id}")
    public PostVo<InjuredStatistics> delInjuredStatistics(@PathVariable("id") String id) {
        injuredStatisticsMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/injuredStatisticsCopy/{time}")
    public List<InjuredStatistics> injuredStatisticsCopy(@PathVariable("time") int time) {
        return injuredStatisticsMapper.getCopyInjuredStatistics(time * 24);
    }
}
