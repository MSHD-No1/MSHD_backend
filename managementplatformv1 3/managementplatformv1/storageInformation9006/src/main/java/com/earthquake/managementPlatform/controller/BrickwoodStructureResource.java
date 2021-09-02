package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.BrickwoodStructure;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SquareStatistics;
import com.earthquake.managementPlatform.mapper.BrickwoodStructureMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BrickwoodStructureResource {
    @Resource
    BrickwoodStructureMapper brickwoodStructureMapper;

    @GetMapping("/v1/brickwoodStructure")
    public GetVo<BrickwoodStructure> brickwoodStructureAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = brickwoodStructureMapper.getAllBrickwoodStructure().size();
        List<BrickwoodStructure> brickwoodStructure = brickwoodStructureMapper.getBrickwoodStructureByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, brickwoodStructure);
    }

    @GetMapping("/v1/brickwoodStructure/{time}")
    public GetVo<BrickwoodStructure> brickwoodStructureByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = brickwoodStructureMapper.getRecentBrickwoodStructure(timestamp).size();
        List<BrickwoodStructure> brickwoodStructures = brickwoodStructureMapper.getRecentBrickwoodStructureByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, brickwoodStructures);

    }

    @GetMapping("/v1/lastBrickwoodStructureByTime")
    public GetVo<SquareStatistics> getLastBrickwoodStructureByTime() {
        List<SquareStatistics> squareStatistics = brickwoodStructureMapper.getLastBrickwoodStructureByTime();
        return new GetVo<>(0, "获取数据成功！", squareStatistics.size(), squareStatistics);
    }

    @GetMapping("/v1/lastBrickwoodStructure")
    public GetVo<BrickwoodStructure> getLastBrickwoodStructure() {
        List<BrickwoodStructure> brickwoodStructure = brickwoodStructureMapper.getLastBrickwoodStructure();
        return new GetVo<>(0, "获取数据成功！", brickwoodStructure.size(), brickwoodStructure);
    }

    @PutMapping("/v1/brickwoodStructure/{id}")
    public PostVo<BrickwoodStructure> editBrickwoodStructure(HttpServletRequest request, @PathVariable("id") String id) {
        BrickwoodStructure brickwoodStructure = new BrickwoodStructure();
        brickwoodStructure.setId(id);
        brickwoodStructure.setDate(request.getParameter("date"));
        brickwoodStructure.setLocation(request.getParameter("location"));
        brickwoodStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        brickwoodStructure.setDamagedSquare(Double.valueOf(request.getParameter("damagedSquare")));
        brickwoodStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        brickwoodStructure.setNote(request.getParameter("note"));
        brickwoodStructure.setReportingUnit(request.getParameter("reportingUnit"));
        brickwoodStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        brickwoodStructureMapper.update(brickwoodStructure);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/brickwoodStructure/{id}")
    public PostVo<BrickwoodStructure> delBrickwoodStructure(@PathVariable("id") String id) {
        brickwoodStructureMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/brickwoodStructureCopy/{time}")
    public List<BrickwoodStructure> brickwoodStructureCopy(@PathVariable("time") int time) {
        return brickwoodStructureMapper.getCopyBrickwoodStructure(time * 24);
    }
}
