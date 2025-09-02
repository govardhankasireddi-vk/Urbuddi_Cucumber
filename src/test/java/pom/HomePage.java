package pom;

import Utilities.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends ElementUtils {


    @FindBy(xpath = "//ul//p[text() = 'Employees']")
    WebElement employeeBtn;

    @FindBy(xpath = "//button[text() ='Add Employee']")
    WebElement addEmployeeBtn;

    @FindBy(xpath = "//div[@role='status']")
    WebElement employeeConfirmationStatus;


    @FindBy(xpath = "//div[@role ='row'][@row-index='0']//div[@aria-colindex='4']")
    WebElement actualEmployeeID;

    @FindBy(xpath = "//input[contains(@aria-label,'EMP ID')]")
    WebElement employeeIDFilter;

    @FindBy(xpath = "//p[text()='Logout']")
    WebElement logoutBtn;

    @FindBy(xpath = "//div/button[text()='Yes']")
    WebElement logoutYes;

    By EmployeeIDInput = By.xpath("//div[@role ='row'][@row-index='0']//div[@aria-colindex='4']");


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void clickEmployeePage() {
        click(employeeBtn);
    }

    public void clickEmployeeBtn() {
        visibilityOfElement(addEmployeeBtn, 10L);
        click(addEmployeeBtn);
    }

    public void enterEmployeeIDInFilter(String id) {
        sendKeysWhenVisible(employeeIDFilter, id);

    }

    public String getActualEmployeeID() {

        return getText(actualEmployeeID);

    }

    public void waitForActualResult() {
        textNotToBePresentwait(EmployeeIDInput, getActualEmployeeID());
    }


    public String getActualStatus() {

        return getText(employeeConfirmationStatus);
    }

    public void clickOnlogoutBtn() {
        click(logoutBtn);
        click(logoutYes);
    }

    public boolean logoutBtnDisplayed() {
        visibilityOfElement(logoutBtn, 10L);
        return logoutBtn.isDisplayed();
    }

    public boolean confirmMessageNotDisplayed() {

        try {
            invisibilityOfElement(employeeConfirmationStatus);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
