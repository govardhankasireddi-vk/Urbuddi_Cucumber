package stepDefinitions;


import Basepack.DriverManager;
import Utilities.ExcelUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pom.EmployeePage;
import pom.HomePage;
import pom.loginPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelStep {

    public final Logger logger = LoggerFactory.getLogger(ExcelStep.class);
    public List<Map<String, String>> dataList;
    loginPage loginPage;
    HomePage homePage;
    EmployeePage employeePage;
    String Employeeid;
    WebDriver driver = DriverManager.getDriver();


    public ExcelStep() {
        loginPage = new loginPage(driver);
        employeePage = new EmployeePage(driver);
        homePage = new HomePage(driver);
    }


    @Given("Launch the browser")
    public void launch_the_browser() {

        logger.info("Browser started");
        System.out.println("browser loaded");


    }

    @When("User loads data from excel {string} and sheet {string}")
    public void userLoadsExcelFromAndSheet(String path, String sheet) throws IOException {
        dataList = ExcelUtils.readAllRows(path, sheet);
    }

    @And("add employees in the portal")
    public void enterAllTheemployeeFields() {

        for (Map<String, String> data : dataList) {
            String URL = data.get("URL");
            String email = data.get("email");
            String password = data.get("password");
            String lastName = data.get("Lastname");
            String firstName = data.get("Firstname1");
            Employeeid = data.get("empID");
            String emailid = data.get("emailID");
            String department = data.get("department");
            String designation = data.get("designation");
            String mob = data.get("Phone");
            String location = data.get("location");
            String qualification = data.get("qualification");
            String gender1 = data.get("gender1");
            String BloodGroup = data.get("BloodGroup");
            String dob = data.get("dob");
            String jod = data.get("jod");
            String reportingTo = data.get("reportingTo");
            String role = data.get("role");
            driver.get(URL);
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickOnloginBtn();
            homePage.clickEmployeePage();
            homePage.clickEmployeeBtn();
            employeePage.setFirstName(firstName);
            employeePage.setLastName(lastName);
            employeePage.setEmployeeid(Employeeid);
            employeePage.setEmailid(emailid);
            employeePage.setPasswordf();
            employeePage.setDepartment(department);
            employeePage.setDesignation(designation);
            employeePage.setMobileNumber(mob);
            employeePage.setLocation(location);
            employeePage.setQualifications(qualification);
            employeePage.setGender(gender1);
            employeePage.setBloodGroup(BloodGroup);
            employeePage.setReportingTo(reportingTo);
            employeePage.setDob(dob);
            employeePage.setJod(jod);
            employeePage.setSalary();
            employeePage.setRole(role);
            logger.info(Employeeid);
            employeePage.clickAddBtn();
            Assert.assertEquals(homePage.getActualStatus(), "Saved Successfully");
            homePage.clickOnlogoutBtn();
        }
    }
}

