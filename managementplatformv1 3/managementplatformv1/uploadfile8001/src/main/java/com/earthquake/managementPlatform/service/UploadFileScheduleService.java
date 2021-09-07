package com.earthquake.managementPlatform.service;

import com.alibaba.druid.util.StringUtils;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.OnOffMapper;
import com.earthquake.managementPlatform.mapper.ScheduleMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
@EnableScheduling
public class UploadFileScheduleService implements SchedulingConfigurer {
    private static String cron = "* * * * * *";
    @Resource
    ScheduleMapper scheduleMapper;
    @Resource
    OnOffMapper onOffMapper;
    @Resource
    UploadFileService uploadFileService;
    @Resource
    FtpFileMethod ftpFileMethod;
    @Resource
    FtpPredictionFileMethod ftpPredictionFileMethod;
    @Resource
    private RestTemplate restTemplate;
    @Value("${disasterInfoCode.url}")
    private String disasterInfoCodeUrl;
    @Value("${informationStorage.url}")
    private String informationStorageUrl;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(doTask(), getTrigger());
    }

    private Runnable doTask() {
        return new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Map<JSONArray, String> map = uploadFileService.uploadEarthquakeInfoFiles();
                if (map != null)
                    log.info(restTemplate.postForObject(disasterInfoCodeUrl + "/v1/disasterInfoCode", map, PostVo.class).getData().toString());
                else
                    log.info("目前无基本震情文件");
                boolean status = onOffMapper.getReadingFiles();
                if (status) {
                    map = uploadFileService.uploadFiles(ftpFileMethod);
                    if (map != null)
                        log.info(restTemplate.postForObject(disasterInfoCodeUrl + "/v1/disasterInfoCode", map, PostVo.class).getData().toString());
                    else
                        log.info("目前无灾情文件");
                    map = uploadFileService.uploadFiles(ftpPredictionFileMethod);
                    if (map != null)
                        log.info(restTemplate.postForObject(informationStorageUrl + "/v1/informationPredictionStorage", map, PostVo.class).getMsg());
                    else
                        log.info("目前无灾情预测文件");
                } else {
                    log.info("读取文件开关未开启！");
                }
            }
        };
    }

    private Trigger getTrigger() {
        return new Trigger() {
            @SneakyThrows
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger trigger = new CronTrigger(getCron());
                return trigger.nextExecutionTime(triggerContext);
            }
        };
    }

    public String getCron() throws Exception {
        String newCron = scheduleMapper.getSchedule();
        if (StringUtils.isEmpty(newCron)) {
            throw new Exception("The config cron expression is empty");
        }
        if (!newCron.equals(cron)) {
            log.info("Cron has been changed to:'" + newCron + "'. Old cron was:'" + cron + "'");
            cron = newCron;
        }
        return cron;
    }
}
