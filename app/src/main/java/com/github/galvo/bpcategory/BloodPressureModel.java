package com.github.galvo.bpcategory;

public class BloodPressureModel {

    private String operation;
    private int systolic;
    private int diastolic;
    private String summary;

    public BloodPressureModel() {

    }

    public BloodPressureModel(String operation) {
        this.operation = operation;
    }

    public BloodPressureModel(int systolic, int diastolic) {
        this.systolic = systolic;
        this.diastolic = diastolic;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
