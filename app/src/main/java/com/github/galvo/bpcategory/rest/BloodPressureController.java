package com.github.galvo.bpcategory.rest;

import com.github.galvo.bpcategory.BloodPressureCategoryCalculator;
import com.github.galvo.bpcategory.BloodPressureCategory;
import com.github.galvo.bpcategory.BloodPressureModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BloodPressureController {

    private BloodPressureCategoryCalculator bloodPressureCalculator;

    @Autowired
    public void setBloodPressureCategoryCalculator(BloodPressureCategoryCalculator bloodPressureCalculator) {
        this.bloodPressureCalculator = bloodPressureCalculator;
    }

    @RequestMapping(value = "/calculator", method = RequestMethod.GET)
    public String getCalculatorPage(Model model){
        model.addAttribute("bloodPressureModel", new BloodPressureModel());
        model.addAttribute("result", "");
        return "calculator";
    }

    @RequestMapping(value="/calculator", params="submit", method = RequestMethod.POST)
    public String getCategory(@ModelAttribute("bloodPressureModel") BloodPressureModel bloodPressureModel, Model model) {
        try {
            BloodPressureCategory category = bloodPressureCalculator.getCategory(
                    bloodPressureModel.getSystolic(), bloodPressureModel.getDiastolic());
            model.addAttribute("result", category.name());
        } catch (Exception ex) {
            model.addAttribute("result", ex.getMessage());
        }
        return "calculator";
    }

    @RequestMapping(value="/calculator", params="reset", method = RequestMethod.POST)
    public String clear(@ModelAttribute("bloodPressureModel") BloodPressureModel bloodPressureModel, Model model) {
        bloodPressureModel.setDiastolic(0);
        bloodPressureModel.setSystolic(0);
        model.addAttribute("bloodPressureModel", bloodPressureModel);
        model.addAttribute("result", "");
        return "calculator";
    }
}
