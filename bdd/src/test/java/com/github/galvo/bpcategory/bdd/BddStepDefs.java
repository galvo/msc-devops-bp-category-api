package com.github.galvo.bpcategory.bdd;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class BddStepDefs {

    private Logger logger = LoggerFactory.getLogger(BddStepDefs.class);

    private WebDriver driver;
    private static String HOST;
    private static String BROWSER;
    private static String WEBDRIVER_PATH;

    static {
        System.setProperty("logging.level.root", "DEBUG");
    }

    @Before
    public void before(Scenario scenario) {
        HOST = System.getProperty("host");
        BROWSER = System.getProperty("browser");
        WEBDRIVER_PATH = System.getProperty("webdriverPath");
        logger.debug("Before Scenario() path:{}", WEBDRIVER_PATH, System.getenv(WEBDRIVER_PATH));

        if (HOST == null || BROWSER == null || WEBDRIVER_PATH == null) {
            throw new RuntimeException(
                    "Either 'host', 'browser', 'webdriverPath' were not defined as command line arguments "
                            + "e.g. \n'mvn verify -Dhost=http://localhost:8085/calculator -Dbrowser=chrome -DwebdriverPath=/usr/local/bin/chromedriver'");
        }
        String driverName = null;
        if ("chrome".equals(BROWSER.toLowerCase())) {
            driverName = "webdriver.chrome.driver";
        }
        logger.debug("Before Scenario() browser:{} driver:{}, path:{}", BROWSER, driverName, WEBDRIVER_PATH);
        System.setProperty(driverName, WEBDRIVER_PATH);
        driver = new ChromeDriver();
        logger.debug("Before Scenario() *** Finished ***");
    }
    
    @After
    public void after(Scenario scenario) {
        logger.debug("After Scenario() ***  Start   *** [{}]", scenario.getName());
        driver.quit();
        logger.debug("After Scenario() *** Finished ***");
    }

    @Given("browser is open on homepage")
    public void browser_is_open_on_homepage()  {
        driver.get(HOST);
    }

    @And("the (.+) value is (\\d+)")
    public void the_field_value_is_x(String field, Integer value)  {
        WebElement element = driver.findElement(By.name(field));
        element.clear();
        element.sendKeys(value.toString());
    }

    @When("i click submit")
    public void i_click_submit()  {
        driver.findElement(By.name("submit")).click();
    }


    @Then("it should display (.+).")
    public void service_is_healthy(String displayText)  {
        WebElement resultElement = driver.findElement(By.id("resultText"));
        assertThat(resultElement.getText()).isEqualTo(displayText);
    }
}
