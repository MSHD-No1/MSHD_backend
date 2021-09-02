package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.OtherStructure;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SquareStatistics;
import com.earthquake.managementPlatform.mapper.OtherStructureMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OtherStructureResource {
    @Resource
    OtherStructureMapper otherStructureMapper;

    @GetMapping("/v1/otherStructure")
    public GetVo<OtherStructure> otherStructureAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = otherStructureMapper.getAllOtherStructure().size();
        List<OtherStructure> otherStructure = otherStructureMapper.getOtherStructureByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, otherStructure);
    }

    @GetMapping("/v1/otherStructure/{time}")
    public GetVo<OtherStructure> otherStructureByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = otherStructureMapper.getRecentOtherStructure(timestamp).size();
        List<OtherStructure> otherStructures = otherStructureMapper.getRecentOtherStructureByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, otherStructures);
    }

    @GetMapping("/v1/lastOtherStructureByTime")
    public GetVo<SquareStatistics>  getLastOtherStructureByTime() {
        List<SquareStatistics> squareStatistics = otherStructureMapper.getLastOtherStructureByTime();
        return new GetVo<>(0, "获取数据成功！", squareStatistics.size(), squareStatistics);
    }

    @GetMapping("/v1/lastOtherStructure")
    public GetVo<OtherStructure> getLastOtherStructure() {
        List<OtherStructure> otherStructure = otherStructureMapper.getLastOtherStructure();
        return new GetVo<>(0, "获取数据成功！", otherStructure.size(), otherStructure);
    }

    @PutMapping("/v1/otherStructure/{id}")
    public PostVo<OtherStructure> editOtherStructure(HttpServletRequest request, @PathVariable("id") String id) {
        OtherStructure otherStructure = new OtherStructure();
        otherStructure.setId(id);
        otherStructure.setDate(request.getParameter("date"));
        otherStructure.setLocation(request.getParameter("location"));
        otherStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        otherStructure.setSlightDamagedSquare(Double.valueOf(request.getParameter("slightDamagedSquare")));
        otherStructure.setModerateDamagedSquare(Double.valueOf(request.getParameter("moderateDamagedSquare")));
        otherStructure.setSeriousDamagedSquare(Double.valueOf(request.getParameter("seriousDamagedSquare")));
        otherStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        otherStructure.setNote(request.getParameter("note"));
        otherStructure.setReportingUnit(request.getParameter("reportingUnit"));
        otherStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        otherStructureMapper.update(otherStructure);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/otherStructure/{id}")
    public PostVo<OtherStructure> delOtherStructure(@PathVariable("id") String id) {
        otherStructureMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/otherStructureCopy/{time}")
    public List<OtherStructure> otherStructureCopy(@PathVariable("time") int time) {
        return otherStructureMapper.getCopyOtherStructure(time * 24);
    }
}
