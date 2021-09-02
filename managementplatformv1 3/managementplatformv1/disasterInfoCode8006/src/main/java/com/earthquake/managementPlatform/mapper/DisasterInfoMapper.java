package com.earthquake.managementPlatform.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DisasterInfoMapper {
    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    String getSomeDisasterInfoByACId(String adminCateId);
}
