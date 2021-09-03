package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.SettlementRecord;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SettlementRecordMapper {

    @Insert("INSERT INTO `earthquakebackup`.`settlementrecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(SettlementRecord settlementRecord);

    @Update("UPDATE `earthquakebackup`.`settlementrecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(SettlementRecord settlementRecord);

}
