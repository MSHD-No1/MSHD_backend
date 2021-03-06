package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IrrigationDisasterMapper {
    @Select("SELECT max(ID) FROM earthquake.irrigationdisaster WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    String getSomeIrrigationDisasterByACId(String adminCateId);
}
