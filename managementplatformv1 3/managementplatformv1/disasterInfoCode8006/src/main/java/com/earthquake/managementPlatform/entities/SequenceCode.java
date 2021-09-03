package com.earthquake.managementPlatform.entities;

import com.earthquake.managementPlatform.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Slf4j
@Repository
public class SequenceCode {
    @Resource
    DistributionCodeMapper distributionCodeMapper;
    @Resource
    DisasterInfoMapper disasterInfoMapper;
    @Resource
    BrickwoodStructureMapper brickwoodStructureMapper;
    @Resource
    CivilStructureMapper civilStructureMapper;
    @Resource
    CollapseRecordMapper collapseRecordMapper;
    @Resource
    CommDisasterMapper commDisasterMapper;
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
    OtherRecordMapper otherRecordMapper;
    @Resource
    OtherStructureMapper otherStructureMapper;
    @Resource
    PowerDisasterMapper powerDisasterMapper;
    @Resource
    SettlementRecordMapper settlementRecordMapper;
    @Resource
    TrafficDisasterMapper trafficDisasterMapper;
    @Resource
    WaterDisasterMapper waterDisasterMapper;

    public String CodeForSequence(String administrativeRegionCode, String categoryCode) {
        String lastCode = "";
        log.info(administrativeRegionCode + "..." + categoryCode);
        DistributionCode distributionCode = distributionCodeMapper.getDistributionCodeById(administrativeRegionCode + categoryCode);
        if (distributionCode != null) {
            distributionCode.setNumber(distributionCode.getNumber() + 1);
            distributionCodeMapper.update(distributionCode);
        } else {
            switch (categoryCode) {
                case "111":
                    lastCode = deathStatisticsMapper.getSomeDeathStatisticsByACId(administrativeRegionCode + categoryCode);
                    break;
                case "112":
                    lastCode = injuredStatisticsMapper.getSomeInjuredStatisticsByACId(administrativeRegionCode + categoryCode);
                    break;
                case "113":
                    lastCode = missingStatisticsMapper.getSomeMissingStatisticsByACId(administrativeRegionCode + categoryCode);
                    break;
                case "221":
                    lastCode = civilStructureMapper.getSomeCivilStructureByACId(administrativeRegionCode + categoryCode);
                    break;
                case "222":
                    lastCode = brickwoodStructureMapper.getSomeBrickwoodStructureByACId(administrativeRegionCode + categoryCode);
                    break;
                case "223":
                    lastCode = masonryStructureMapper.getSomeMasonryStructureByACId(administrativeRegionCode + categoryCode);
                    break;
                case "224":
                    lastCode = frameworkStructureMapper.getSomeFrameworkStructureByACId(administrativeRegionCode + categoryCode);
                    break;
                case "225":
                    lastCode = otherStructureMapper.getSomeOtherStructureByACId(administrativeRegionCode + categoryCode);
                    break;
                case "331":
                    lastCode = trafficDisasterMapper.getSomeTrafficDisasterByACId(administrativeRegionCode + categoryCode);
                    break;
                case "332":
                    lastCode = waterDisasterMapper.getSomeWaterDisasterByACId(administrativeRegionCode + categoryCode);
                    break;
                case "333":
                    lastCode = oilDisasterMapper.getSomeOilDisasterByACId(administrativeRegionCode + categoryCode);
                    break;
                case "334":
                    lastCode = gasDisasterMapper.getSomeGasDisasterByACId(administrativeRegionCode + categoryCode);
                    break;
                case "335":
                    lastCode = powerDisasterMapper.getSomePowerDisasterByACId(administrativeRegionCode + categoryCode);
                    break;
                case "336":
                    lastCode = commDisasterMapper.getSomeCommDisasterByACId(administrativeRegionCode + categoryCode);
                    break;
                case "337":
                    lastCode = irrigationDisasterMapper.getSomeIrrigationDisasterByACId(administrativeRegionCode + categoryCode);
                    break;
                case "441":
                    lastCode = collapseRecordMapper.getSomeCollapseRecordByACId(administrativeRegionCode + categoryCode);
                    break;
                case "442":
                    lastCode = landslideRecordMapper.getSomeLandslideRecordByACId(administrativeRegionCode + categoryCode);
                    break;
                case "443":
                    lastCode = debrisRecordMapper.getSomeDebrisRecordByACId(administrativeRegionCode + categoryCode);
                    break;
                case "444":
                    lastCode = karstRecordMapper.getSomeKarstRecordByACId(administrativeRegionCode + categoryCode);
                    break;
                case "445":
                    lastCode = crackRecordMapper.getSomeCrackRecordByACId(administrativeRegionCode + categoryCode);
                    break;
                case "446":
                    lastCode = settlementRecordMapper.getSomeSettlementRecordByACId(administrativeRegionCode + categoryCode);
                    break;
                case "447":
                    lastCode = otherRecordMapper.getSomeOtherRecordByACId(administrativeRegionCode + categoryCode);
                    break;
            }

            if (lastCode != null && !lastCode.equals("")) {
                int codeCount = Integer.parseInt(lastCode.substring(15, 18)) + 1;
                distributionCode = new DistributionCode(administrativeRegionCode + categoryCode, codeCount);
            } else {
                distributionCode = new DistributionCode(administrativeRegionCode + categoryCode, 1);
            }
            distributionCodeMapper.save(distributionCode);
        }

        return String.format("%3d", distributionCode.getNumber()).replace(" ", "0");
    }
}
