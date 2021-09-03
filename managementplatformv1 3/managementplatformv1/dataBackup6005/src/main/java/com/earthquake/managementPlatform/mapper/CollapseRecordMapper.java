package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.CollapseRecord;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CollapseRecordMapper {

    @Insert("INSERT INTO `earthquakebackup`.`collapserecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(CollapseRecord collapseRecord);

    @Update("UPDATE `earthquakebackup`.`collapserecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(CollapseRecord collapseRecord);

}
