package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.CommDisaster;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CommDisasterMapper {

    @Insert("INSERT INTO `earthquakebackup`.`commdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(CommDisaster commDisaster);

    @Update("UPDATE `earthquakebackup`.`commdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(CommDisaster commDisaster);


}
