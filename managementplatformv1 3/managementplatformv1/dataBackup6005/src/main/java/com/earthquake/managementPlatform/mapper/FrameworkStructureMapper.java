package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.FrameworkStructure;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FrameworkStructureMapper {

    @Insert("INSERT INTO `earthquakebackup`.`frameworkstructure` (`ID`, `date`, `location`, `basically_intact_square`, `slight_damaged_square`, `moderate_damaged_square`, `serious_damaged_square`, `destroyed_square`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{basicallyIntactSquare},#{slightDamagedSquare},#{moderateDamagedSquare},#{seriousDamagedSquare},#{destroyedSquare},#{note},#{reportingUnit},#{earthquakeId})")
    int save(FrameworkStructure frameworkStructure);

    @Update("UPDATE `earthquakebackup`.`frameworkstructure` SET `date`=#{date}, `location`=#{location}, `basically_intact_square`=#{basicallyIntactSquare}, `slight_damaged_square`=#{slightDamagedSquare}, `moderate_damaged_square`=#{moderateDamagedSquare}, `serious_damaged_square`=#{seriousDamagedSquare}, `destroyed_square`=#{destroyedSquare}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(FrameworkStructure frameworkStructure);

}
