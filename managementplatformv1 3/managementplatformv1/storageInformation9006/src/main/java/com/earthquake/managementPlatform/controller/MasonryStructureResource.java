package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.MasonryStructure;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.SquareStatistics;
import com.earthquake.managementPlatform.mapper.MasonryStructureMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MasonryStructureResource {
    @Resource
    MasonryStructureMapper masonryStructureMapper;

    @GetMapping("/v1/masonryStructure")
    public GetVo<MasonryStructure> masonryStructureAll(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = masonryStructureMapper.getAllMasonryStructure().size();
        List<MasonryStructure> masonryStructure = masonryStructureMapper.getMasonryStructureByPage((page - 1) * limit, limit);
        return new GetVo<>(0, "获取数据成功！", size, masonryStructure);
    }

    @GetMapping("/v1/masonryStructure/{time}")
    public GetVo<MasonryStructure> masonryStructureByTime(@PathVariable("time") int time, HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time * 24;
        int size = masonryStructureMapper.getRecentMasonryStructure(timestamp).size();
        List<MasonryStructure> masonryStructures = masonryStructureMapper.getRecentMasonryStructureByPage((page - 1) * limit, limit, timestamp);
        return new GetVo<>(0, "获取数据成功！", size, masonryStructures);
    }

    @GetMapping("/v1/lastMasonryStructureByTime")
    public GetVo<SquareStatistics> getLastMasonryStructureByTime() {
        List<SquareStatistics> squareStatistics = masonryStructureMapper.getLastMasonryStructureByTime();
        return new GetVo<>(0, "获取数据成功！", squareStatistics.size(), squareStatistics);
    }

    @GetMapping("/v1/lastMasonryStructure")
    public GetVo<MasonryStructure> getLastMasonryStructure() {
        List<MasonryStructure> masonryStructure = masonryStructureMapper.getLastMasonryStructure();
        return new GetVo<>(0, "获取数据成功！", masonryStructure.size(), masonryStructure);
    }

    @PutMapping("/v1/masonryStructure/{id}")
    public PostVo<MasonryStructure> editMasonryStructure(HttpServletRequest request, @PathVariable("id") String id) {
        MasonryStructure masonryStructure = new MasonryStructure();
        masonryStructure.setId(id);
        masonryStructure.setDate(request.getParameter("date"));
        masonryStructure.setLocation(request.getParameter("location"));
        masonryStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        masonryStructure.setSlightDamagedSquare(Double.valueOf(request.getParameter("slightDamagedSquare")));
        masonryStructure.setModerateDamagedSquare(Double.valueOf(request.getParameter("moderateDamagedSquare")));
        masonryStructure.setSeriousDamagedSquare(Double.valueOf(request.getParameter("seriousDamagedSquare")));
        masonryStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        masonryStructure.setNote(request.getParameter("note"));
        masonryStructure.setReportingUnit(request.getParameter("reportingUnit"));
        masonryStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        masonryStructureMapper.update(masonryStructure);
        return new PostVo<>(0, "编辑成功！", null);
    }

    @DeleteMapping("/v1/masonryStructure/{id}")
    public PostVo<MasonryStructure> delMasonryStructure(@PathVariable("id") String id) {
        masonryStructureMapper.deleteById(id);
        return new PostVo<>(0, "删除成功!", null);
    }

    @GetMapping("/v1/masonryStructureCopy/{time}")
    public List<MasonryStructure> masonryStructureCopy(@PathVariable("time") int time) {
        return masonryStructureMapper.getCopyMasonryStructure(time * 24);
    }
}
