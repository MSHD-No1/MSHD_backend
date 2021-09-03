package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CodeDescriptionFactory {
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

    public Object createCodeDescription(String categoryId) {

        Object object = new Object();

        switch (categoryId) {
            case "111":
                object = deathStatisticsMapper.getNewCodeDescription();
                break;
            case "112":
                object = injuredStatisticsMapper.getNewCodeDescription();
                break;
            case "113":
                object = missingStatisticsMapper.getNewCodeDescription();
                break;
            case "221":
                object = civilStructureMapper.getNewCodeDescription();
                break;
            case "222":
                object = brickwoodStructureMapper.getNewCodeDescription();
                break;
            case "223":
                object = masonryStructureMapper.getNewCodeDescription();
                break;
            case "224":
                object = frameworkStructureMapper.getNewCodeDescription();
                break;
            case "225":
                object = otherStructureMapper.getNewCodeDescription();
                break;
            case "331":
                object = trafficDisasterMapper.getNewCodeDescription();
                break;
            case "332":
                object = waterDisasterMapper.getNewCodeDescription();
                break;
            case "333":
                object = oilDisasterMapper.getNewCodeDescription();
                break;
            case "334":
                object = gasDisasterMapper.getNewCodeDescription();
                break;
            case "335":
                object = powerDisasterMapper.getNewCodeDescription();
                break;
            case "336":
                object = commDisasterMapper.getNewCodeDescription();
                break;
            case "337":
                object = irrigationDisasterMapper.getNewCodeDescription();
                break;
            case "441":
                object = collapseRecordMapper.getNewCodeDescription();
                break;
            case "442":
                object = landslideRecordMapper.getNewCodeDescription();
                break;
            case "443":
                object = debrisRecordMapper.getNewCodeDescription();
                break;
            case "444":
                object = karstRecordMapper.getNewCodeDescription();
                break;
            case "445":
                object = crackRecordMapper.getNewCodeDescription();
                break;
            case "446":
                object = settlementRecordMapper.getNewCodeDescription();
                break;
            case "447":
                object = otherRecordMapper.getNewCodeDescription();
                break;
            case "551":
                object = basicEarthquakeInfoMapper.getNewCodeDescription();
                break;
        }
        return object;

    }
}
