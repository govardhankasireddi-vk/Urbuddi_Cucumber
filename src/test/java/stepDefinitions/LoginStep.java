package stepDefinitions;


import Basepack.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pom.Employee_page;
import pom.Home_page;
import pom.login_page;

import java.util.List;
import java.util.Map;

import static Utilities.CommonUtils.resolveRandomValue;


public class LoginStep {

    public final Logger logger = LoggerFactory.getLogger(LoginStep.class);
    login_page loginPage;
    Home_page homePage;
    Employee_page employeePage;
    String Employeeid;
    WebDriver driver = DriverManager.getDriver();

    public LoginStep() {
        loginPage = new login_page(driver);
        homePage = new Home_page(driver);
        employeePage = new Employee_page(driver);

    }

    @Given("Launch the broswer")
    public void launch_the_broswer() {
        logger.info("Browser started");

    }

    @When("User open the URL: {string}")
    public void user_open_the_url(String URL) {
        driver.get(URL);
        logger.info("URL opened");

    }

    @Then("Title Should contains {string}")
    public void titleShouldContains(String string) throws InterruptedException {
        String s = driver.getTitle();
        logger.info("Page loaded sucessfully");
    }

    @When("User Enters Email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);

    }

    @When("Click on Login button.")
    public void click_on_login_button() {
        loginPage.clickOnloginBtn();
    }

    @Then("Page Should contains {string}")
    public void page_should_have(String text) throws InterruptedException {
        Assert.assertTrue(homePage.logoutBtnDisplayed());
        logger.info("logged in sucessfullly");
    }

    @When("User open the URL:")
    public void user_open_the_url(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        String URL = data.get(0).get("URL");
        driver.get(URL);
        logger.info("URL called");

    }

    @When("User Enters Email  and password")
    public void user_enters_email_and_password(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        String email = data.get(0).get("email");
        String password = data.get(0).get("password");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);

    }

    @Then("Close Browser")
    public void close_browser() throws InterruptedException {
        driver.close();

    }

    @When("Click on Employee icon and add Employee")
    public void clickOnEmployeeIconAndAddEmployee() throws InterruptedException {

        homePage.clickEmployeePage();
        homePage.clickEmployeeBtn();
        logger.info("Employee page opened");

    }

    @Then("Option to enter all the required fields should display")
    public void enterAllTheRequiredFields(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();

        String firstName = resolveRandomValue(data.get(0).get("Firstname"));
        String LastName = data.getFirst().get("Lastname");

        String lastName = resolveRandomValue(LastName);
        Employeeid = resolveRandomValue(data.get(0).get("empID"));
        String emailid = resolveRandomValue(data.get(0).get("email"));
        String department = data.get(0).get("department");
        String mobileNumber = resolveRandomValue(data.get(0).get("Phone"));
        String designation = data.get(0).get("designation");
        String location = data.get(0).get("location");
        String qualification = data.get(0).get("qualification");
        String gender1 = data.get(0).get("gender1");
        String BloodGroup = data.get(0).get("BloodGroup");
        String dob = data.get(0).get("dob");
        String jod = data.get(0).get("jod");
        String reportingTo = data.get(0).get("reportingTo");
        String role = data.get(0).get("role");


        employeePage.setFirstName(firstName);
        employeePage.setLastName(lastName);
        employeePage.setEmployeeid(Employeeid);
        employeePage.setEmailid(emailid);
        employeePage.setPasswordf();
        employeePage.setDepartment(department);
        employeePage.setDesignation(designation);
        employeePage.setMobileNumber(mobileNumber);
        employeePage.setLocation(location);
        employeePage.setQualifications(qualification);
        employeePage.setGender(gender1);
        employeePage.setBloodGroup(BloodGroup);
        employeePage.setReportingTo(reportingTo);
        employeePage.setDob(dob);
        employeePage.setJod(jod);
        employeePage.setSalary();
        employeePage.setRole(role);
    }

    @And("Click on add button")
    public void clickOnAddButton() throws InterruptedException {
        employeePage.clickAddBtn();
        if (employeePage.checkAlertMsg()) {
            Employeeid = resolveRandomValue("OWRandom_id");
            employeePage.setEmployeeid(Employeeid);
            employeePage.clickAddBtn();
        } else {
            logger.info("Employee ID updated");
        }

    }

    @Then("Employee should be add succesfully.")
    public void employeeShouldBeAddSuccesfully() throws InterruptedException {
        Assert.assertEquals("Saved Successfully", homePage.getActualStatus());
        homePage.enterEmployeeIDInFilter(Employeeid);
        homePage.waitForActualResult();
        Assert.assertEquals(Employeeid, homePage.getActualEmployeeID());
    }
}




