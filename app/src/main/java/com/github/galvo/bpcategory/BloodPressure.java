package com.github.galvo.bpcategory;

import org.springframework.stereotype.Service;

@Service
public class BloodPressure {

    public static final int SYSTOLIC_MIN = 70;
    public static final int SYSTOLIC_MAX = 190;
    public static final int DIASTOLIC_MIN = 40;
    public static final int DIASTOLIC_MAX = 100;

    public BloodPressureCategory getBloodPressureCategory(int systolic, int diastolic) {
        boolean invalidSystolicValue = false;
        boolean invalidDiastolicValue = false;
        if (systolic < SYSTOLIC_MIN || systolic > SYSTOLIC_MAX) {
            invalidSystolicValue = true;
        }
        if (diastolic < DIASTOLIC_MIN || diastolic > DIASTOLIC_MAX) {
            invalidDiastolicValue = true;
        }
        if (invalidSystolicValue && invalidDiastolicValue) {
            throw new IllegalArgumentException("Invalid Systolic & Diastolic Values");
        } else if (invalidSystolicValue) {
            throw new IllegalArgumentException("Invalid Systolic Value");
        } else if (invalidDiastolicValue) {
            throw new IllegalArgumentException("Invalid Diastolic Value");
        }

        if ((systolic >= SYSTOLIC_MIN && systolic < 90)
                && (diastolic >= DIASTOLIC_MIN && diastolic < 60)) {
            return BloodPressureCategory.LOW;
        } else if ((systolic >= 90 && systolic < 120)
                && (diastolic >= 60 && diastolic < 80)) {
            return BloodPressureCategory.NORMAL;
        } else if ((systolic >= 120 && systolic < 140)
                && (diastolic >= 80 && diastolic < 90)) {
            return BloodPressureCategory.PRE_HIGH;
        } else if ((systolic >= 140 && systolic <= SYSTOLIC_MAX)
                && (diastolic >= 90 && diastolic <= DIASTOLIC_MAX)) {
            return BloodPressureCategory.HIGH;
        } else {
            throw new IllegalArgumentException("Out of range");
        }
    }
}
