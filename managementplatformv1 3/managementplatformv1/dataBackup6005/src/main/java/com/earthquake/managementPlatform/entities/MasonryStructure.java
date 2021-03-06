package com.earthquake.managementPlatform.entities;

public class MasonryStructure {
    private String id;
    private String date;
    private String location;
    private Double basicallyIntactSquare;
    private Double slightDamagedSquare;
    private Double moderateDamagedSquare;
    private Double seriousDamagedSquare;
    private Double destroyedSquare;
    private String note;
    private String reportingUnit;
    private String earthquakeId;

    public MasonryStructure() {
    }

    public MasonryStructure(String id, String date, String location, Double basicallyIntactSquare, Double slightDamagedSquare, Double moderateDamagedSquare, Double seriousDamagedSquare, Double destroyedSquare, String note, String reportingUnit, String earthquakeId) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.basicallyIntactSquare = basicallyIntactSquare;
        this.slightDamagedSquare = slightDamagedSquare;
        this.moderateDamagedSquare = moderateDamagedSquare;
        this.seriousDamagedSquare = seriousDamagedSquare;
        this.destroyedSquare = destroyedSquare;
        this.note = note;
        this.reportingUnit = reportingUnit;
        this.earthquakeId = earthquakeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getBasicallyIntactSquare() {
        return basicallyIntactSquare;
    }

    public void setBasicallyIntactSquare(Double basicallyIntactSquare) {
        this.basicallyIntactSquare = basicallyIntactSquare;
    }

    public Double getSlightDamagedSquare() {
        return slightDamagedSquare;
    }

    public void setSlightDamagedSquare(Double slightDamagedSquare) {
        this.slightDamagedSquare = slightDamagedSquare;
    }

    public Double getModerateDamagedSquare() {
        return moderateDamagedSquare;
    }

    public void setModerateDamagedSquare(Double moderateDamagedSquare) {
        this.moderateDamagedSquare = moderateDamagedSquare;
    }

    public Double getSeriousDamagedSquare() {
        return seriousDamagedSquare;
    }

    public void setSeriousDamagedSquare(Double seriousDamagedSquare) {
        this.seriousDamagedSquare = seriousDamagedSquare;
    }

    public Double getDestroyedSquare() {
        return destroyedSquare;
    }

    public void setDestroyedSquare(Double destroyedSquare) {
        this.destroyedSquare = destroyedSquare;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReportingUnit() {
        return reportingUnit;
    }

    public void setReportingUnit(String reportingUnit) {
        this.reportingUnit = reportingUnit;
    }

    public String getEarthquakeId() {
        return earthquakeId;
    }

    public void setEarthquakeId(String earthquakeId) {
        this.earthquakeId = earthquakeId;
    }

    @Override
    public String toString() {
        return "???????????????????????????????????????????????????" + '\n' +
                "?????????" + location + '???' + '\n' +
                "???????????????" + date + '???' + '\n' +
                "?????????????????????" + basicallyIntactSquare + '???' + '\n' +
                "?????????????????????" + slightDamagedSquare + '???' + '\n' +
                "?????????????????????" + moderateDamagedSquare + '???' + '\n' +
                "?????????????????????" + seriousDamagedSquare + '???' + '\n' +
                "???????????????" + destroyedSquare + '???' + '\n' +
                "?????????????????????" + note + '???' + '\n' +
                "???????????????" + reportingUnit + '???' + '\n' +
                "????????????????????????????????????4?????????3?????????2?????????1??????\n?????????" + id.substring(18);
    }
}
