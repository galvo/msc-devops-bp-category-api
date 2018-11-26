package com.github.galvo.bpcategory.rest;

import com.github.galvo.bpcategory.BloodPressure;
import com.github.galvo.bpcategory.BloodPressureCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BloodPressureController {

    @Autowired
    private BloodPressure bloodPressure;

    @RequestMapping(value = "/blood-pressure",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@RequestParam(value = "systolic") Integer systolic,
            @RequestParam(value = "diastolic") Integer diastolic) {
        System.out.println("systolic=" +systolic+" diastolic=" + diastolic);
        try {
            BloodPressureCategory category = bloodPressure.getBloodPressureCategory(systolic, diastolic);
            return new ResponseEntity<BloodPressureCategory>(category, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
        }
    }
}
