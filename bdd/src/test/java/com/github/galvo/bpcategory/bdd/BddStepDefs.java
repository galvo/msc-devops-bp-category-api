package com.github.galvo.bpcategory.bdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class BddStepDefs {

    private Logger logger = LoggerFactory.getLogger(BddStepDefs.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static String HOST;
    private static String PORT;
    private static String BASEPATH;

    static {
        System.setProperty("logging.level.root", "DEBUG");
    }

    @Before
    public void before(Scenario scenario) {
        HOST = System.getProperty("host");
        PORT = System.getProperty("port");
        BASEPATH = System.getProperty("basePath");
        if (HOST == null || PORT == null || BASEPATH == null) {
            throw new RuntimeException(
                    "Either 'host', 'port', 'basePath' were not defined as command line arguments "
                            + "e.g. \n'mvn verify -Dhost=127.0.0.1 -Dport=80 -DbasePath=/blah'");
        }

        RestAssured.reset();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        RestAssured.baseURI = "http://" + HOST + ":" + PORT + BASEPATH;
        logger.debug("Before Scenario() ***  Start   *** [{}] HttpTarget:{}", scenario.getName(), RestAssured.baseURI);

        System.setProperty("logging.level.root", "DEBUG");

        logger.debug("Before Scenario() *** Finished ***");
    }
    
    @After
    public void after(Scenario scenario) {
        logger.debug("After Scenario() ***  Start   *** [{}]", scenario.getName());

        logger.debug("After Scenario() *** Finished ***");
    }

    @Given("service is healthy")
    public void service_is_healthy()  {

    }

    @And("a (.+) request to (.+) will respond with status (\\d+) and payload:$")
    public void mock_api(String httpVerb, String url, Integer responseStatus, String responseBody)  {

    }
}
