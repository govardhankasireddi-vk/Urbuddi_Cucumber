package stepDefinitions;

import Basepack.DriverManager;
import Utilities.ConfigReader;
import Utilities.ScreenshotUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pom.DeleteEmployee_Page;
import pom.Home_page;
import pom.login_page;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeleteStep {
        WebDriver driver = DriverManager.getDriver();
        String URL = ConfigReader.getProperty("url");
        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");
        String  name = ConfigReader.getProperty("name");

    public final Logger logger = LoggerFactory.getLogger(DeleteStep.class);

        login_page loginPage;
        Home_page homePage;
        DeleteEmployee_Page deleteEmployeePage;
       public DeleteStep(){
      loginPage = new login_page(driver);
      homePage = new Home_page(driver);
      deleteEmployeePage = new DeleteEmployee_Page(driver);

       }

    public static void afterEachStep() {
        String timeStamp = new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
        byte[] screenshot = ScreenshotUtil.takeScreenshot(DriverManager.getDriver());

        Allure.addAttachment(
                "Step Screenshot - " + timeStamp,
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png"
        );
    }

        @When("User open the URL from config")
        public void userOpenTheURLFromConfig() {
            driver.get(URL);
            afterEachStep();
        }

        @And("User Enters Email and password from config")
        public void userEntersEmailAndPasswordFromConfig() {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
            Allure.addAttachment("entered details",  "entered these details" + " "+"email:" + email + " " + "password:"+ password);
            afterEachStep();
        }

        @When("Click on Employee icon and delete the employee")
        public void clickOnEmployeeIconAndDeleteTheEmployee() throws InterruptedException {
            homePage.clickEmployeePage();
            deleteEmployeePage.setName(name);
            if(deleteEmployeePage.employeesfilitered()) {
                for (int i = 1; i <= deleteEmployeePage.getMaxPageNo(); i++) {
                    deleteEmployeePage.deleteEmployee();
                    if (deleteEmployeePage.NextPageBtnEnabled()) {
                        deleteEmployeePage.clickNextPageBtn();
                    }
                }
                deleteEmployeePage.clickDeleteBtn();
            }
            else{
                logger.info("no employees are there for delete");
                Allure.addAttachment("deleted users", "no users are to delete");
            }
            afterEachStep();

        }

    @Then("Employee should be delete succesfully.")
    public void employeeShouldBeDeleteSuccesfully() {
        Assert.assertEquals("Employee Deleted Successfully", homePage.getActualStatus());
        afterEachStep();
    }
}



