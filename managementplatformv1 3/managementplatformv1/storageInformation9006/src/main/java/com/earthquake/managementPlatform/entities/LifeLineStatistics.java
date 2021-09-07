package com.earthquake.managementPlatform.entities;

public class LifeLineStatistics {
    private String grade;
    private int count;

    public LifeLineStatistics() {
    }

    public LifeLineStatistics(String grade, int count) {
        this.grade = grade;
        this.count = count;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
