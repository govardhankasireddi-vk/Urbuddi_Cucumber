package runner;

import io.cucumber.plugin.Plugin;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;
import io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm;
import org.testng.annotations.BeforeClass;

import java.io.File;


//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
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

    @BeforeSuite
    public void cleanAllureResults() {
        File allureResults = new File("allure-results");
        if (allureResults.exists() && allureResults.isDirectory()) {
            deleteDirectory(allureResults);
        }
    }

    private void deleteDirectory(File directory) {
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directory.delete();
        //excel updated
    }

}


