package com.github.galvo.bpcategory.rest;

import com.github.galvo.bpcategory.BloodPressureCategoryCalculator;
import com.github.galvo.bpcategory.BloodPressureCategory;
import com.github.galvo.bpcategory.BloodPressureModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BloodPressureController {

    private BloodPressureCategoryCalculator bloodPressureCalculator = new BloodPressureCategoryCalculator();
    private static final String ATTRIBUTE_RESULT = "result";
    private static final String ATTRIBUTE_BLOOD_PRESSURE_MODEL = "bloodPressureModel";
    private static final String ATTRIBUTE_SUMMARY = "summary";
    private static final String VIEW_CALCULATOR = "calculator";
    private static final String ENDPOINT_CALCULATOR = "/calculator";

    @GetMapping(value = ENDPOINT_CALCULATOR)
    public String getCalculatorPage(Model model){
        model.addAttribute(ATTRIBUTE_BLOOD_PRESSURE_MODEL, new BloodPressureModel());
        model.addAttribute(ATTRIBUTE_RESULT, "");
        model.addAttribute(ATTRIBUTE_SUMMARY, "");
        return VIEW_CALCULATOR;
    }

    @PostMapping(value = ENDPOINT_CALCULATOR, params="submit")
    public String getCategory(@ModelAttribute(ATTRIBUTE_BLOOD_PRESSURE_MODEL) BloodPressureModel bloodPressureModel, Model model) {
        try {
            BloodPressureCategory category = bloodPressureCalculator.getCategory(
                    bloodPressureModel.getSystolic(), bloodPressureModel.getDiastolic());
            model.addAttribute(ATTRIBUTE_RESULT, category.getDisplayName());
            model.addAttribute(ATTRIBUTE_SUMMARY, category.getSummary());
        } catch (Exception ex) {
            model.addAttribute(ATTRIBUTE_RESULT, ex.getMessage());
            model.addAttribute(ATTRIBUTE_SUMMARY, "");
        }
        return VIEW_CALCULATOR;
    }

    @PostMapping(value = ENDPOINT_CALCULATOR, params="reset")
    public String clear(@ModelAttribute(ATTRIBUTE_BLOOD_PRESSURE_MODEL) BloodPressureModel bloodPressureModel, Model model) {
        bloodPressureModel.setDiastolic(0);
        bloodPressureModel.setSystolic(0);
        model.addAttribute(ATTRIBUTE_BLOOD_PRESSURE_MODEL, bloodPressureModel);
        model.addAttribute(ATTRIBUTE_RESULT, "");
        model.addAttribute(ATTRIBUTE_SUMMARY, "");
        return VIEW_CALCULATOR;
    }
}
