package com.earthquake.managementPlatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisasterPrediction {
    private String D_ID;
    private String S_ID;
    private String date;
    private String grade;
    private String intensity;
    private String type;
    private String picture;


    public String getD_ID() {
        return D_ID;
    }

    public void setD_ID(String d_ID) {
        D_ID = d_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getS_ID() {
        return S_ID;
    }

    public void setS_ID(String s_ID) {
        S_ID = s_ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
