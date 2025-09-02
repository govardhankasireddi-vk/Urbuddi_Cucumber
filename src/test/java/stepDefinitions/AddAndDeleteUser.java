package stepDefinitions;

import Basepack.DriverManager;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pom.DeleteEmployeePage;
import pom.EmployeePage;
import pom.HomePage;

public class AddAndDeleteUser {

    WebDriver driver = DriverManager.getDriver();
    DeleteEmployeePage deleteEmployeePage;
    EmployeePage employeePage;
    HomePage homePage;

    public AddAndDeleteUser(){
        deleteEmployeePage = new DeleteEmployeePage(driver);
        employeePage = new EmployeePage(driver);
        homePage = new HomePage(driver);
    }


    @Then("Select the above employee")
    public void select_the_above_employee() {
        deleteEmployeePage.deleteEmployee();
    }

    @Then("delete the employee.")
    public void delete_the_employee() {
        deleteEmployeePage.clickDeleteBtn();
        Assert.assertEquals(homePage.getActualStatus(), "Employee Deleted Successfully");
        Allure.addAttachment("employee status updated", "Registered employee deleted");
        DeleteStep.afterEachStep();
    }
}
