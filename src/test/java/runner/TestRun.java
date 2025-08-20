package runner;

import io.cucumber.plugin.Plugin;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;
import io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm;
import org.testng.annotations.BeforeClass;


//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/deleteUser.feature",
        glue = {"stepDefinitions"},
        dryRun = false,
        monochrome = true,
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "pretty"
        }
)

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestRun extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        Object[][] scenarios;
        scenarios = super.scenarios();
        return scenarios;
    }

}


