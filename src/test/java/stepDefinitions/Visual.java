package stepDefinitions;

import Basepack.DriverManager;
import Utilities.ConfigReader;
import Utilities.ImageDifference;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pom.HomePage;
import pom.loginPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import java.io.IOException;

public class Visual {

    WebDriver driver = DriverManager.getDriver();
    String URL = ConfigReader.getProperty("url");
    String email = ConfigReader.getProperty("email");
    String password = ConfigReader.getProperty("password");
    String  name = ConfigReader.getProperty("name");
    Screenshot currentScreenshot;
    Screenshot changedScreenshot;


    loginPage loginPage;
    HomePage homePage;
    //DeleteEmployeePage deleteEmployeePage;

    public Visual(){
        loginPage = new loginPage(driver);
        homePage = new HomePage(driver);
        //deleteEmployeePage = new DeleteEmployeePage(driver);

    }

    @When("user login into the application")
    public void user_login_into_the_application() {
        driver.get(URL);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickOnloginBtn();

    }
    @When("click on the employee icon")
    public void click_on_the_employee_icon() {
        homePage.clickEmployeePage();

    }
    @When("change the employee page columns")
    public void change_the_employee_page_columns() throws InterruptedException {
        Thread.sleep(5000);
        homePage.waitForEmployeeIDVisiblity();
        currentScreenshot = new AShot().takeScreenshot(driver);
        homePage.moveEmployeeIDColumn();
        System.out.println("moved");
        Thread.sleep(3000);
         changedScreenshot = new AShot().takeScreenshot(driver);
    }
    @Then("the differences in the UI should be display")
    public void the_differences_in_the_ui_should_be_display() throws IOException {
        ImageDifference.imageDiff(changedScreenshot.getImage(),currentScreenshot.getImage());
        System.out.println("changed");
    }

}
