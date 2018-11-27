package com.github.galvo.bpcategory.tests;

import com.github.galvo.bpcategory.BloodPressure;
import com.github.galvo.bpcategory.BloodPressureCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class BloodPressureTest {

    @ParameterizedTest
    @MethodSource("invalidSystolicRange")
    @DisplayName("Test IllegalArgumentException thrown for Invalid Systolic Range")
    public void testInvalidSystolicRange(int systolicValue) {
        // given
        BloodPressure bloodPressure = new BloodPressure();

        // when
        Throwable thrown = catchThrowable(() -> {
            bloodPressure.getBloodPressureCategory(systolicValue, BloodPressure.DIASTOLIC_MIN); });

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Systolic Value");
    }

    @ParameterizedTest
    @MethodSource("invalidDiastolicRange")
    @DisplayName("Test IllegalArgumentException thrown for Invalid Diastolic Range")
    public void testInvalidDiastolicRange(int diastolicValue) {
        // given
        BloodPressure bloodPressure = new BloodPressure();

        // when
        Throwable thrown = catchThrowable(() -> {
            bloodPressure.getBloodPressureCategory(BloodPressure.SYSTOLIC_MIN, diastolicValue); });

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid Diastolic Value");
    }

    @ParameterizedTest
    @MethodSource("invalidSystolicAndDiastolicRange")
    @DisplayName("Test IllegalArgumentException thrown for Invalid Systolic & Diastolic Values")
    public void testInvalidSystolicAndDiastolicRange(int[] systolicValues, int[] diastolicValues) {
        // given
        BloodPressure bloodPressure = new BloodPressure();
        for (int i = 0; i < systolicValues.length; i++) {
            for (int j = 0; j < diastolicValues.length; j++) {
                final int systolicValue = systolicValues[i];
                final int diastolicValue = diastolicValues[j];

                // when
                Throwable thrown = catchThrowable(() -> {
                    bloodPressure.getBloodPressureCategory(systolicValue, diastolicValue);
                });

                // then
                assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("Invalid Systolic & Diastolic Values");
            }
        }
    }

    @ParameterizedTest
    @MethodSource("invalidOutOfRangeCategoryRanges")
    @DisplayName("Test IllegalArgumentException thrown for Out of Range systolic and diastolic values")
    public void testOutOfRangeIllegalArgumentException(int[] systolicValues, int[] diastolicValues) {
        // given
        BloodPressure bloodPressure = new BloodPressure();
        for (int i = 0; i < systolicValues.length; i++) {
            for (int j = 0; j < diastolicValues.length; j++) {
                final int systolicValue = systolicValues[i];
                final int diastolicValue = diastolicValues[j];

                // when
                Throwable thrown = catchThrowable(() -> {
                    bloodPressure.getBloodPressureCategory(systolicValue, diastolicValue);
                });

                // then
                assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("Out of range");
            }
        }
    }

    @ParameterizedTest
    @MethodSource("validLowBloodPressureCategoryRanges")
    @DisplayName("Test valid Low Blood Pressure Category ranges")
    public void testValidLowCategoryRanges(int[] systolicValues, int[] diastolicValues) {
        // given
        BloodPressure bloodPressure = new BloodPressure();
        for (int i = 0; i < systolicValues.length; i++) {
            for (int j = 0; j < diastolicValues.length; j++) {
                // when
                BloodPressureCategory category = bloodPressure.getBloodPressureCategory(systolicValues[i], diastolicValues[j]);

                // then
                assertThat(category).isEqualTo(BloodPressureCategory.LOW);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("validIdealBloodPressureCategoryRanges")
    @DisplayName("Test valid Normal Blood Pressure Category ranges")
    public void testIdealBloodPressureCategoryRanges(int[] systolicValues, int[] diastolicValues) {
        // given
        BloodPressure bloodPressure = new BloodPressure();
        for (int i = 0; i < systolicValues.length; i++) {
            for (int j = 0; j < diastolicValues.length; j++) {
                // when
                BloodPressureCategory category = bloodPressure.getBloodPressureCategory(systolicValues[i], diastolicValues[j]);

                // then
                assertThat(category).isEqualTo(BloodPressureCategory.NORMAL);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("validPreHighBloodPressureCategoryRanges")
    @DisplayName("Test valid Pre High Blood Pressure Category ranges")
    public void testValidPreHighBloodPressureCategoryRanges(int[] systolicValues, int[] diastolicValues) {
        // given
        BloodPressure bloodPressure = new BloodPressure();
        for (int i = 0; i < systolicValues.length; i++) {
            for (int j = 0; j < diastolicValues.length; j++) {
                // when
                BloodPressureCategory category = bloodPressure.getBloodPressureCategory(systolicValues[i], diastolicValues[j]);

                // then
                assertThat(category).isEqualTo(BloodPressureCategory.PRE_HIGH);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("validHighBloodPressureCategoryRanges")
    @DisplayName("Test valid High Blood Pressure Category ranges")
    public void testValidHighBloodPressureCategoryRanges(int[] systolicValues, int[] diastolicValues) {
        // given
        BloodPressure bloodPressure = new BloodPressure();
        for (int i = 0; i < systolicValues.length; i++) {
            for (int j = 0; j < diastolicValues.length; j++) {
                // when
                BloodPressureCategory category = bloodPressure.getBloodPressureCategory(systolicValues[i], diastolicValues[j]);

                // then
                assertThat(category).isEqualTo(BloodPressureCategory.HIGH);
            }
        }
    }

    private static IntStream invalidSystolicRange() {
        return IntStream.concat(
                IntStream.range(0, BloodPressure.SYSTOLIC_MIN - 1),
                IntStream.range(BloodPressure.SYSTOLIC_MAX + 1, BloodPressure.SYSTOLIC_MAX + 10));
    }

    private static IntStream invalidDiastolicRange() {
        return IntStream.concat(
                IntStream.range(0, BloodPressure.DIASTOLIC_MIN - 1),
                IntStream.range(BloodPressure.DIASTOLIC_MAX + 1, BloodPressure.DIASTOLIC_MAX + 10));
    }

    static Stream<Arguments> invalidSystolicAndDiastolicRange() {
        return Stream.of(
                Arguments.of(invalidSystolicRange().toArray(), invalidDiastolicRange().toArray())
        );
    }

    static Stream<Arguments> invalidOutOfRangeCategoryRanges() {
        return Stream.of(Arguments.of(getLowSystolicRange(), getIdealDiastolicRange()));
    }

    static Stream<Arguments> validLowBloodPressureCategoryRanges() {
        return Stream.of(Arguments.of(getLowSystolicRange(), getLowDiastolicRange()));
    }

    static Stream<Arguments> validIdealBloodPressureCategoryRanges() {
        return Stream.of(Arguments.of(getIdealSystolicRange(), getIdealDiastolicRange()));
    }

    static Stream<Arguments> validPreHighBloodPressureCategoryRanges() {
        return Stream.of(Arguments.of(getPreHighSystolicRange(), getPreHighDiastolicRange()));
    }

    static Stream<Arguments> validHighBloodPressureCategoryRanges() {
        return Stream.of(Arguments.of(getHighSystolicRange(), getHighDiastolicRange()));
    }

    private static int[] getLowSystolicRange() {
        return getIntArray(BloodPressure.SYSTOLIC_MIN, 89);
    }

    private static int[] getLowDiastolicRange() {
        return getIntArray(BloodPressure.DIASTOLIC_MIN, 59);
    }

    private static int[] getIdealSystolicRange() {
        return getIntArray(90, 119);
    }

    private static int[] getIdealDiastolicRange() {
        return getIntArray(60, 79);
    }

    private static int[] getPreHighSystolicRange() {
        return getIntArray(120, 139);
    }

    private static int[] getPreHighDiastolicRange() {
        return getIntArray(80, 89);
    }

    private static int[] getHighSystolicRange() {
        return getIntArray(140, 190);
    }

    private static int[] getHighDiastolicRange() {
        return getIntArray(90, 100);
    }

    private static int[] getIntArray(int min, int max) {
        return IntStream.range(min, max).toArray();
    }
}
