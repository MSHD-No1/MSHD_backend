package com.earthquake.managementPlatform.service;

import com.alibaba.druid.util.StringUtils;
import com.earthquake.managementPlatform.mapper.BackupTimeMapper;
import com.earthquake.managementPlatform.mapper.ScheduleMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
@EnableScheduling
public class BackupScheduleService implements SchedulingConfigurer {
    private static String cron = "* * * * * *";
    @Resource
    ScheduleMapper scheduleMapper;
    @Resource
    BackupService backupService;

    @Resource
    BackupTimeMapper backupTimeMapper;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(doTask(), getTrigger());
    }

    private Runnable doTask() {
        return () -> {
            int time = backupTimeMapper.getTime();
            if (backupService.backUp(time)) {
                log.info("备份成功！");
            } else {
                log.info("备份失败！");
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
            //new StringBuffer("Cron has been changed to:'").append(newCron).append("'. Old cron was:'").append(cron).append("'").toString();
            log.info("Cron has been changed to:'" + newCron + "'. Old cron was:'" + cron + "'");
            cron = newCron;
        }
        return cron;
    }
}
