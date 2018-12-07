package com.github.galvo.bpcategory.tests;

import com.github.galvo.bpcategory.BloodPressureCategory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class BloodPressureCategoryTest {

    @Test
    @DisplayName("Test Low BloodPressureCategory enum fields")
    public void lowBloodPressureCategoryEnumFields() {
        BloodPressureCategory bloodPressureCategory = BloodPressureCategory.LOW;
        assertThat(bloodPressureCategory.getDisplayName()).isEqualTo("Low Blood Pressure");
        assertThat(bloodPressureCategory.name()).isEqualTo("LOW");
    }

    @Test
    @DisplayName("Test Normal BloodPressureCategory enum fields")
    public void normalBloodPressureCategoryEnumFields() {
        BloodPressureCategory bloodPressureCategory = BloodPressureCategory.NORMAL;
        assertThat(bloodPressureCategory.getDisplayName()).isEqualTo("Normal Blood Pressure");
        assertThat(bloodPressureCategory.name()).isEqualTo("NORMAL");
    }

    @Test
    @DisplayName("Test Pre High BloodPressureCategory enum fields")
    public void preHighBloodPressureCategoryEnumFields() {
        BloodPressureCategory bloodPressureCategory = BloodPressureCategory.PRE_HIGH;
        assertThat(bloodPressureCategory.getDisplayName()).isEqualTo("Pre-High Blood Pressure");
        assertThat(bloodPressureCategory.name()).isEqualTo("PRE_HIGH");
    }

    @Test
    @DisplayName("Test High BloodPressureCategory enum fields")
    public void highBloodPressureCategoryEnumFields() {
        BloodPressureCategory bloodPressureCategory = BloodPressureCategory.HIGH;
        assertThat(bloodPressureCategory.getDisplayName()).isEqualTo("High Blood Pressure");
        assertThat(bloodPressureCategory.name()).isEqualTo("HIGH");
    }
}
