package stepDefinitions;

import Basepack.DriverManager;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pom.DeleteEmployee_Page;
import pom.Employee_page;
import pom.Home_page;

public class AddAndDeleteUser {

    WebDriver driver = DriverManager.getDriver();
    DeleteEmployee_Page deleteEmployeePage;
    Employee_page employeePage;
    Home_page homePage;

    public AddAndDeleteUser(){
        deleteEmployeePage = new DeleteEmployee_Page(driver);
        employeePage = new Employee_page(driver);
        homePage = new Home_page(driver);
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
