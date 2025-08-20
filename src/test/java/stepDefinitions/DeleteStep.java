package stepDefinitions;

import Basepack.DriverManager;
import Utilities.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pom.DeleteEmployee_Page;
import pom.Home_page;
import pom.login_page;

public class DeleteStep {
        WebDriver driver = DriverManager.getDriver();
        String URL = ConfigReader.getProperty("url");
        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");
        String  name = ConfigReader.getProperty("name");


        login_page loginPage;
        Home_page homePage;
        DeleteEmployee_Page deleteEmployeePage;
       public DeleteStep(){
      loginPage = new login_page(driver);
      homePage = new Home_page(driver);
      deleteEmployeePage = new DeleteEmployee_Page(driver);

       }

        @When("User open the URL from config")
        public void userOpenTheURLFromConfig() {
            driver.get(URL);
        }

        @And("User Enters Email and password from config")
        public void userEntersEmailAndPasswordFromConfig() {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        }

        @When("Click on Employee icon and delete the employee")
        public void clickOnEmployeeIconAndDeleteTheEmployee() throws InterruptedException {
            homePage.clickEmployeePage();
            deleteEmployeePage.setName(name);
            if(deleteEmployeePage.employeesfilitered()) {
                for (int i = 1; i <= 1; i++) {
                    deleteEmployeePage.deleteEmployee();
                    deleteEmployeePage.clickNextPageBtn();
                }
            }
            deleteEmployeePage.clickDeleteBtn();
            System.out.println("delete employee");
        }
    }
   // deleteEmployeePage.getMaxPageNo()


