package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.GasDisaster;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GasDisasterMapper {

    @Insert("INSERT INTO `earthquakebackup`.`gasdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(GasDisaster gasDisaster);

    @Update("UPDATE `earthquakebackup`.`gasdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(GasDisaster gasDisaster);

}
