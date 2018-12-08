package com.github.galvo.bpcategory.integration.tests;

import com.github.galvo.bpcategory.BloodPressureCategory;
import com.github.galvo.bpcategory.BloodPressureCategoryCalculator;
import com.github.galvo.bpcategory.BloodPressureModel;
import com.github.galvo.bpcategory.rest.BloodPressureController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BloodPressureController.class)
public class BloodPressureControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void getCalculatorPage() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/calculator"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("calculator"))
                .andExpect(MockMvcResultMatchers.view().name("calculator"))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("systolic", is(0))))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("diastolic", is(0))))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("operation", nullValue())))
                .andExpect(model().attribute("result", equalTo("")))
                .andDo(print());
    }

    @Test
    public void getCategory() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        BloodPressureModel bloodPressureModel = new BloodPressureModel();
        bloodPressureModel.setSystolic(85);
        bloodPressureModel.setDiastolic(55);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator")
                .param("systolic", "85")
                .param("diastolic", "55")
                .param("submit", "Submit")
                .sessionAttr("bloodPressureModel", bloodPressureModel))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("calculator"))
                .andExpect(MockMvcResultMatchers.view().name("calculator"))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("systolic", is(85))))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("diastolic", is(55))))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("operation", nullValue())))
                .andExpect(model().attribute("result", equalTo(BloodPressureCategory.LOW.getDisplayName())))
                .andDo(print());
    }

    @Test
    public void getOutOfRangeErrorForCategory() throws Exception {
        int systolic = 110;
        int diastolic = 55;
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        BloodPressureModel bloodPressureModel = new BloodPressureModel();
        bloodPressureModel.setSystolic(systolic);
        bloodPressureModel.setDiastolic(diastolic);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator")
                .param("systolic", String.valueOf(systolic))
                .param("diastolic", String.valueOf(diastolic))
                .param("submit", "Submit")
                .sessionAttr("bloodPressureModel", bloodPressureModel))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("calculator"))
                .andExpect(MockMvcResultMatchers.view().name("calculator"))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("systolic", is(systolic))))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("diastolic", is(diastolic))))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("operation", nullValue())))
                .andExpect(model().attribute("result", equalTo("Out of range")))
                .andDo(print());
    }

    @Test
    public void clearSimple() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/calculator")
                .param("reset", "Reset")
                .sessionAttr("bloodPressureModel", new BloodPressureModel()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("calculator"))
                .andExpect(MockMvcResultMatchers.view().name("calculator"))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("systolic", is(0))))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("diastolic", is(0))))
                .andExpect(model().attribute("bloodPressureModel", hasProperty("operation", nullValue())))
                .andExpect(model().attribute("result", equalTo("")))
                .andDo(print());
    }
}
