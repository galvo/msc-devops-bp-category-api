package com.github.galvo.bpcategory;

public enum BloodPressureCategory {

    LOW("Low Blood Pressure"),
    NORMAL("Normal Blood Pressure"),
    PRE_HIGH("Pre-High Blood Pressure"),
    HIGH("High Blood Pressure");

    private String displayName;

    BloodPressureCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
