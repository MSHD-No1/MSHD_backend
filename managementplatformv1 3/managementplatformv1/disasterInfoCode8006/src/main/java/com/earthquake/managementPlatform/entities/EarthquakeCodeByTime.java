package com.earthquake.managementPlatform.entities;

public class EarthquakeCodeByTime implements EarthquakeCode {

    private final String administrativeRegionCode;
    private final String time;

    public EarthquakeCodeByTime(String administrativeRegionCode, String time) {
        this.administrativeRegionCode = administrativeRegionCode;
        this.time = time;
    }

    @Override
    public String codeForEarthquakeCode() {
        String time = this.time.replace("-", "").replace(" ", "").replace(":", "");
        return administrativeRegionCode + time;
    }
}
