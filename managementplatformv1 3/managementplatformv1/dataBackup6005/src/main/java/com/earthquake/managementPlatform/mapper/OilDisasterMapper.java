package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.OilDisaster;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OilDisasterMapper {

    @Insert("INSERT INTO `earthquakebackup`.`oildisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(OilDisaster oilDisaster);

    @Update("UPDATE `earthquakebackup`.`oildisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(OilDisaster oilDisaster);

}
