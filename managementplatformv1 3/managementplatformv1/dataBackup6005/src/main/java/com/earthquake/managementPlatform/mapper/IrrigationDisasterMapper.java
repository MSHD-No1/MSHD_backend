package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.IrrigationDisaster;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface IrrigationDisasterMapper {

    @Insert("INSERT INTO `earthquakebackup`.`irrigationdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(IrrigationDisaster IrrigationDisaster);

    @Update("UPDATE `earthquakebackup`.`irrigationdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(IrrigationDisaster IrrigationDisaster);

}
