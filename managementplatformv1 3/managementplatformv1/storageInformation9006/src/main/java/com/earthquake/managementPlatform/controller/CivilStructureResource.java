package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.CivilStructure;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SquareStatistics;
import com.earthquake.managementPlatform.mapper.CivilStructureMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CivilStructureResource {
    @Resource
    CivilStructureMapper civilStructureMapper;

    @GetMapping("/v1/civilStructure")
    public GetVo<CivilStructure> civilStructureAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = civilStructureMapper.getAllCivilStructure().size();
        List<CivilStructure> civilStructure = civilStructureMapper.getCivilStructureByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, civilStructure);
    }

    @GetMapping("/v1/civilStructure/{time}")
    public GetVo<CivilStructure> civilStructureByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = civilStructureMapper.getRecentCivilStructure(timestamp).size();
        List<CivilStructure> civilStructures = civilStructureMapper.getRecentCivilStructureByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, civilStructures);

    }

    @GetMapping("/v1/lastCivilStructureByTime")
    public GetVo<SquareStatistics> getLastCivilStructureByTime() {
        List<SquareStatistics> squareStatistics = civilStructureMapper.getLastCivilStructureByTime();
        return new GetVo<>(0, "获取数据成功！", squareStatistics.size(), squareStatistics);
    }

    @GetMapping("/v1/lastCivilStructure")
    public GetVo<CivilStructure> getLastCivilStructure() {
        List<CivilStructure> civilStructure = civilStructureMapper.getLastCivilStructure();
        return new GetVo<>(0, "获取数据成功！", civilStructure.size(), civilStructure);
    }

    @PutMapping("/v1/civilStructure/{id}")
    public PostVo<CivilStructure> editCivilStructure(HttpServletRequest request, @PathVariable("id") String id) {
        CivilStructure civilStructure = new CivilStructure();
        civilStructure.setId(id);
        civilStructure.setDate(request.getParameter("date"));
        civilStructure.setLocation(request.getParameter("location"));
        civilStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        civilStructure.setDamagedSquare(Double.valueOf(request.getParameter("damagedSquare")));
        civilStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        civilStructure.setNote(request.getParameter("note"));
        civilStructure.setReportingUnit(request.getParameter("reportingUnit"));
        civilStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        civilStructureMapper.update(civilStructure);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/civilStructure/{id}")
    public PostVo<CivilStructure> delCivilStructure(@PathVariable("id") String id) {
        civilStructureMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/civilStructureCopy/{time}")
    public List<CivilStructure> civilStructureCopy(@PathVariable("time") int time) {
        return civilStructureMapper.getCopyCivilStructure(time * 24);
    }
}
