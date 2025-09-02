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
import pom.DeleteEmployeePage;
import pom.HomePage;
import pom.loginPage;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeleteStep {
        WebDriver driver = DriverManager.getDriver();
        String URL = ConfigReader.getProperty("url");
        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");
        String  name = ConfigReader.getProperty("name");
        int count;

    public final Logger logger = LoggerFactory.getLogger(DeleteStep.class);

        loginPage loginPage;
        HomePage homePage;
        DeleteEmployeePage deleteEmployeePage;

       public DeleteStep(){
      loginPage = new loginPage(driver);
      homePage = new HomePage(driver);
      deleteEmployeePage = new DeleteEmployeePage(driver);

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
        public void clickOnEmployeeIconAndDeleteTheEmployee()  {
            homePage.clickEmployeePage();
            deleteEmployeePage.setName(name);
            if(deleteEmployeePage.employeesfilitered()) {
                for (int i = 1; i <= deleteEmployeePage.getMaxPageNo(); i++) {
                    deleteEmployeePage.deleteEmployee();
                    if (deleteEmployeePage.NextPageBtnEnabled()) {
                        deleteEmployeePage.clickNextPageBtn();
                    }
                }
                count=1;
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
           if(count==1) {
               Assert.assertEquals(homePage.getActualStatus(), "Employee Deleted Successfully");
               afterEachStep();
           }
    }
}



