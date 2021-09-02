package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.CommDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CommDisasterResource {
    @Resource
    CommDisasterMapper commDisasterMapper;
    @GetMapping("/v1/commDisaster")
    public GetVo<CommDisaster> commDisasterAll(HttpServletRequest request){
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int size = commDisasterMapper.getAllCommDisaster().size();
        List<CommDisaster> commDisaster = commDisasterMapper.getCommDisasterByPage((page-1)*limit,limit);
        return new GetVo<>(0,"获取数据成功！",size,commDisaster);
    }

    @GetMapping("/v1/commDisaster/{time}")
    public GetVo<CommDisaster> commDisasterByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        int timestamp = time*24;
        int size = commDisasterMapper.getRecentCommDisaster(timestamp).size();
        List<CommDisaster> commDisasters = commDisasterMapper.getRecentCommDisasterByPage((page-1)*limit,limit,timestamp);
        return new GetVo<>(0,"获取数据成功！",size,commDisasters);
    }

    @GetMapping("/v1/lastCommDisasterStatistics")
    public GetVo<LifeLineStatistics> getLastCommDisasterStatistics(){
        List<LifeLineStatistics> lifeLineStatistics = commDisasterMapper.getCommDisasterStatistics();
        return new GetVo<>(0,"获取数据成功！",lifeLineStatistics.size(),lifeLineStatistics);
    }

    @PutMapping("/v1/commDisaster/{id}")
    public PostVo editCommDisaster(HttpServletRequest request, @PathVariable("id") String id){
        CommDisaster commDisaster = new CommDisaster();
        commDisaster.setId(id);
        commDisaster.setDate(request.getParameter("date"));
        commDisaster.setLocation(request.getParameter("location"));
        commDisaster.setType(request.getParameter("type"));
        commDisaster.setGrade(request.getParameter("grade"));
        commDisaster.setPicture(request.getParameter("picture"));
        commDisaster.setNote(request.getParameter("note"));
        commDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        commDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        commDisasterMapper.update(commDisaster);
        return new PostVo(0,"编辑成功！",null);
    }

    @DeleteMapping("/v1/commDisaster/{id}")
    public PostVo delCommDisaster(@PathVariable("id")String id){
        commDisasterMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/commDisasterCopy/{time}")
    public List<CommDisaster> commDisasterCopy(@PathVariable("time") int time){
        return commDisasterMapper.getCopyCommDisaster(time*24);
    }
}
