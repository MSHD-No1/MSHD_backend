package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.MissingStatistics;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MissingStatisticsMapper {

    @Insert("INSERT INTO `earthquakebackup`.`missingstatistics` (`ID`, `location`, `date`, `number`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{location},#{date},#{number},#{reportingUnit},#{earthquakeId})")
    int save(MissingStatistics missingStatistics);

    @Update("UPDATE `earthquakebackup`.`missingstatistics` SET `date`=#{date}, `location`=#{location}, `number`=#{number},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(MissingStatistics missingStatistics);

}
