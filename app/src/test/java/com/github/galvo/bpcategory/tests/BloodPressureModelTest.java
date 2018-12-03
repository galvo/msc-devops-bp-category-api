package com.github.galvo.bpcategory.tests;

import com.github.galvo.bpcategory.BloodPressureCategory;
import com.github.galvo.bpcategory.BloodPressureModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BloodPressureModelTest {

    @Test
    @DisplayName("Test new BloodPressureModel(operation) constructor")
    public void lowBloodPressureModelOperationConstructor() {
        BloodPressureModel model = new BloodPressureModel("submit");
        assertThat(model.getOperation()).isEqualTo("submit");
        assertThat(model.getDiastolic()).isEqualTo(0);
        assertThat(model.getSystolic()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test new BloodPressureModel(systolic, diastolic) constructor")
    public void lowBloodPressureModelSystolicDiastolicConstructor() {
        BloodPressureModel model = new BloodPressureModel(5, 10);
        assertThat(model.getOperation()).isEqualTo(null);
        assertThat(model.getDiastolic()).isEqualTo(10);
        assertThat(model.getSystolic()).isEqualTo(5);
    }

    @Test
    @DisplayName("Test new BloodPressureModel() constructor")
    public void lowBloodPressureModelDefaultConstructor() {
        BloodPressureModel model = new BloodPressureModel();
        assertThat(model.getOperation()).isEqualTo(null);
        assertThat(model.getDiastolic()).isEqualTo(0);
        assertThat(model.getSystolic()).isEqualTo(0);

        model.setOperation("submit");
        assertThat(model.getOperation()).isEqualTo("submit");

        model.setDiastolic(35);
        assertThat(model.getDiastolic()).isEqualTo(35);
        
        model.setSystolic(70);
        assertThat(model.getSystolic()).isEqualTo(70);
    }
}
