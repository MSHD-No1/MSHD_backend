package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.mapper.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CodeFactory {
    @Resource
    BrickwoodStructureMapper brickwoodStructureMapper;
    @Resource
    CivilStructureMapper civilStructureMapper;
    @Resource
    CrackRecordMapper crackRecordMapper;
    @Resource
    DeathStatisticsMapper deathStatisticsMapper;
    @Resource
    DebrisRecordMapper debrisRecordMapper;
    @Resource
    FrameworkStructureMapper frameworkStructureMapper;
    @Resource
    GasDisasterMapper gasDisasterMapper;
    @Resource
    InjuredStatisticsMapper injuredStatisticsMapper;
    @Resource
    IrrigationDisasterMapper irrigationDisasterMapper;
    @Resource
    KarstRecordMapper karstRecordMapper;
    @Resource
    LandslideRecordMapper landslideRecordMapper;
    @Resource
    MasonryStructureMapper masonryStructureMapper;
    @Resource
    MissingStatisticsMapper missingStatisticsMapper;
    @Resource
    OilDisasterMapper oilDisasterMapper;
    @Resource
    OtherStructureMapper otherStructureMapper;
    @Resource
    OtherRecordMapper otherRecordMapper;
    @Resource
    PowerDisasterMapper powerDisasterMapper;
    @Resource
    SettlementRecordMapper settlementRecordMapper;
    @Resource
    TrafficDisasterMapper trafficDisasterMapper;
    @Resource
    WaterDisasterMapper waterDisasterMapper;
    @Resource
    CommDisasterMapper commDisasterMapper;
    @Resource
    CollapseRecordMapper collapseRecordMapper;
    @Resource
    BasicEarthquakeInfoMapper basicEarthquakeInfoMapper;
    @Resource
    DisasterPredictionMapper disasterPredictionMapper;

    public String createCode(String categoryId) {

        String code = "";

        switch (categoryId) {
            case "111":
                code = deathStatisticsMapper.getNewCode();
                break;
            case "112":
                code = injuredStatisticsMapper.getNewCode();
                break;
            case "113":
                code = missingStatisticsMapper.getNewCode();
                break;
            case "221":
                code = civilStructureMapper.getNewCode();
                break;
            case "222":
                code = brickwoodStructureMapper.getNewCode();
                break;
            case "223":
                code = masonryStructureMapper.getNewCode();
                break;
            case "224":
                code = frameworkStructureMapper.getNewCode();
                break;
            case "225":
                code = otherStructureMapper.getNewCode();
                break;
            case "331":
                code = trafficDisasterMapper.getNewCode();
                break;
            case "332":
                code = waterDisasterMapper.getNewCode();
                break;
            case "333":
                code = oilDisasterMapper.getNewCode();
                break;
            case "334":
                code = gasDisasterMapper.getNewCode();
                break;
            case "335":
                code = powerDisasterMapper.getNewCode();
                break;
            case "336":
                code = commDisasterMapper.getNewCode();
                break;
            case "337":
                code = irrigationDisasterMapper.getNewCode();
                break;
            case "441":
                code = collapseRecordMapper.getNewCode();
                break;
            case "442":
                code = landslideRecordMapper.getNewCode();
                break;
            case "443":
                code = debrisRecordMapper.getNewCode();
                break;
            case "444":
                code = karstRecordMapper.getNewCode();
                break;
            case "445":
                code = crackRecordMapper.getNewCode();
                break;
            case "446":
                code = settlementRecordMapper.getNewCode();
                break;
            case "447":
                code = otherRecordMapper.getNewCode();
                break;
            case "551":
                code = basicEarthquakeInfoMapper.getNewCode();
                break;
        }
        return code;

    }


}
