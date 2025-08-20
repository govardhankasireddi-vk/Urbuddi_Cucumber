package stepDefinitions;


import Basepack.DriverManager;
import Utilities.ConfigReader;
import Utilities.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class Hooks {

    String browser = ConfigReader.getProperty("browser");
    public final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void driverIntialize() {
        DriverManager.setDriver(browser);
        logger.info("driver initiated");
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("Starting scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("Failure Screenshot", "image/png",
                    new ByteArrayInputStream(ScreenshotUtil.takeScreenshot(DriverManager.getDriver())), ".png");
            logger.error("Scenario failed: " + scenario.getName());
        } else {
            logger.info("Scenario passed: " + scenario.getName());
        }
        //DriverManager.closeDriver();
    }
}


