package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class DayDateTest {
    @Test
    public void DayDateTest() {
        DayDate dayDate = new DayDate();
        log.info(dayDate.getYear() + dayDate.getMonth() + dayDate.getDay());
    }

}