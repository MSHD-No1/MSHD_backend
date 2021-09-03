package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.DisasterPrediction;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DisasterPredictionMapper {

    @Insert("INSERT INTO `earthquakebackup`.`disasterprediction` (`D_ID`, `S_ID`, `date`, `grade`, `intensity`, `type`, `picture`) VALUES (#{D_ID}, #{S_ID}, #{date}, #{grade}, #{intensity}, #{type}, #{picture});\n")
    int save(DisasterPrediction disasterPrediction);

    @Update("UPDATE `earthquakebackup`.`disasterprediction` SET `date`=#{date}, `grade`=#{grade}, `intensity`=#{intensity}, `type`=#{type}, `picture`=#{picture} WHERE `D_ID`=#{D_ID} AND `S_ID`=#{S_ID}")
    int update(DisasterPrediction disasterPrediction);


}
