package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.FrameworkStructure;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SquareStatistics;
import com.earthquake.managementPlatform.mapper.FrameworkStructureMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class FrameworkStructureResource {
    @Resource
    FrameworkStructureMapper frameworkStructureMapper;

    @GetMapping("/v1/frameworkStructure")
    public GetVo<FrameworkStructure> frameworkStructureAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = frameworkStructureMapper.getAllFrameworkStructure().size();
        List<FrameworkStructure> frameworkStructure = frameworkStructureMapper.getFrameworkStructureByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, frameworkStructure);
    }

    @GetMapping("/v1/frameworkStructure/{time}")
    public GetVo<FrameworkStructure> frameworkStructureByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = frameworkStructureMapper.getRecentFrameworkStructure(timestamp).size();
        List<FrameworkStructure> frameworkStructures = frameworkStructureMapper.getRecentFrameworkStructureByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, frameworkStructures);
    }

    @GetMapping("/v1/lastFrameworkStructureByTime")
    public GetVo getLastFrameworkStructureByTime() {
        List<SquareStatistics> squareStatistics = frameworkStructureMapper.getLastFrameworkStructureByTime();
        return new GetVo<>(0, "获取数据成功！", squareStatistics.size(), squareStatistics);
    }

    @GetMapping("/v1/lastFrameworkStructure")
    public GetVo<FrameworkStructure> getLastFrameworkStructure() {
        List<FrameworkStructure> frameworkStructure = frameworkStructureMapper.getLastFrameworkStructure();
        return new GetVo<>(0, "获取数据成功！", frameworkStructure.size(), frameworkStructure);
    }

    @PutMapping("/v1/frameworkStructure/{id}")
    public PostVo<FrameworkStructure> editFrameworkStructure(HttpServletRequest request, @PathVariable("id") String id) {
        FrameworkStructure frameworkStructure = new FrameworkStructure();
        frameworkStructure.setId(id);
        frameworkStructure.setDate(request.getParameter("date"));
        frameworkStructure.setLocation(request.getParameter("location"));
        frameworkStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        frameworkStructure.setSlightDamagedSquare(Double.valueOf(request.getParameter("slightDamagedSquare")));
        frameworkStructure.setModerateDamagedSquare(Double.valueOf(request.getParameter("moderateDamagedSquare")));
        frameworkStructure.setSeriousDamagedSquare(Double.valueOf(request.getParameter("seriousDamagedSquare")));
        frameworkStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        frameworkStructure.setNote(request.getParameter("note"));
        frameworkStructure.setReportingUnit(request.getParameter("reportingUnit"));
        frameworkStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        frameworkStructureMapper.update(frameworkStructure);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/frameworkStructure/{id}")
    public PostVo<FrameworkStructure> delFrameworkStructure(@PathVariable("id") String id) {
        frameworkStructureMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/frameworkStructureCopy/{time}")
    public List<FrameworkStructure> frameworkStructureCopy(@PathVariable("time") int time) {
        return frameworkStructureMapper.getCopyFrameworkStructure(time * 24);
    }
}
