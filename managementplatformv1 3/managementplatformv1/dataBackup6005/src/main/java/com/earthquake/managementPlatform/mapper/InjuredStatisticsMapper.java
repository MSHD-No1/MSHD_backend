package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.InjuredStatistics;
import org.apache.ibatis.annotations.*;

@Mapper
public interface InjuredStatisticsMapper {

    @Insert("INSERT INTO `earthquakebackup`.`injuredstatistics` (`ID`, `location`, `date`, `number`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{location},#{date},#{number},#{reportingUnit},#{earthquakeId})")
    int save(InjuredStatistics injuredStatistics);

    @Update("UPDATE `earthquakebackup`.`injuredstatistics` SET `date`=#{date}, `location`=#{location}, `number`=#{number},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(InjuredStatistics injuredStatistics);

}
