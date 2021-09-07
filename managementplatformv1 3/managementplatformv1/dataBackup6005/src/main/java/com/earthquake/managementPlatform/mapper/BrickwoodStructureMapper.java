package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.BrickwoodStructure;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BrickwoodStructureMapper {

    @Insert("INSERT INTO `earthquakebackup`.`brickwoodstructure` (`ID`, `date`, `location`, `basically_intact_square`, `damaged_square`, `destroyed_square`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{basicallyIntactSquare},#{damagedSquare},#{destroyedSquare},#{note},#{reportingUnit},#{earthquakeId})")
    int save(BrickwoodStructure brickwoodStructure);

    @Update("UPDATE `earthquakebackup`.`brickwoodstructure` SET `date`=#{date}, `location`=#{location}, `basically_intact_square`=#{basicallyIntactSquare}, `damaged_square`=#{damagedSquare}, `destroyed_square`=#{destroyedSquare}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(BrickwoodStructure brickwoodStructure);


}
