package com.github.galvo.bpcategory;

public enum BloodPressureCategory {

    LOW("Low Blood Pressure", "Some people have a blood pressure level that is lower than normal. In general this may be good news - because the lower your blood pressure is, the lower your risk of stroke or heart disease. However, in a few cases, having low blood pressure can cause problems, so you might need to speak to your doctor or nurse."),
    NORMAL("Normal Blood Pressure", "Your blood pressure reading is ideal and healthy. Follow a healthy lifestyle to keep it at this level"),
    PRE_HIGH("Pre-High Blood Pressure", "You have a normal blood pressure reading but it is a little higher than it should be, and you should try to lower it by making healthy changes to your lifestyle."),
    HIGH("High Blood Pressure", "You may have high blood pressure (hypertension). Change your lifestyle - see your doctor or nurse and take any medicines they may give you.");

    private String displayName;
    private String summary;

    BloodPressureCategory(String displayName, String summary) {
        this.displayName = displayName;
        this.summary = summary;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSummary() {
        return summary;
    }
}
