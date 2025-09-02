package pom;

import Utilities.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Utilities.CommonUtils.name;
import static Utilities.CommonUtils.num;


public class EmployeePage extends ElementUtils {


    @FindBy(xpath = "//input[@name='firstName']")
    WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='lastName']")
    WebElement lastNameInput;

    @FindBy(xpath = "//input[@name='id']")
    WebElement employeeIdInput;

    @FindBy(xpath = "//input[@name='email']")
    WebElement emailIdInput;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@name='department']")
    WebElement departmentInput;

    @FindBy(xpath = "//input[@name='mobileNumber']")
    WebElement mobileNumberInput;

    @FindBy(xpath = "//input[@name='designation']")
    WebElement designationInput;

    @FindBy(xpath = "//input[@name='salary']")
    WebElement salaryInput;

    @FindBy(xpath = "//input[@name='location']")
    WebElement locationInput;


    @FindBy(xpath = "//select[@id='qualifications']")
    WebElement qualificationsDropdown;

    @FindBy(xpath = "//select[@id='gender']")
    WebElement genderDropdown;

    @FindBy(xpath = "//select[@id='bloodGroup']")
    WebElement bloodGroupDropdown;


    @FindBy(xpath = "//select[@id='reportingTo']")
    WebElement reportingToDropdown;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement addBtn;

    @FindBy(xpath = "//input[@name='dob']")
    WebElement dateOfBirthWidget;

    @FindBy(xpath = "//input[@name='joiningDate']")
    WebElement joiningDateWidget;

    @FindBy(xpath = "//select[@name='role']")
    WebElement roleDropdown;

    @FindBy(xpath = "//form[@class='add-employee-form']/p")
    WebElement alertMsg;

    public EmployeePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void setFirstName(String fn) {
        sendKeysWhenVisible(firstNameInput, fn);
    }

    public void setLastName(String lastN) {

        sendKeysWhenVisible(lastNameInput, lastN);
    }

    public void setEmployeeid(String numid) {
        sendKeysWhenVisible(employeeIdInput, numid);

    }

    public void setEmailid(String mail) {
        sendKeysWhenVisible(emailIdInput, mail);
    }

    public void setPasswordf() {
        sendKeysWhenVisible(passwordInput, name() + "@" + num().substring(1, 4));
    }

    public void setDepartment(String dept) {
        sendKeysWhenVisible(departmentInput, dept);
    }

    public void setMobileNumber(String num) {
        sendKeysWhenVisible(mobileNumberInput, num);

    }

    public void setDesignation(String des) {
        designationInput.sendKeys(des);
    }

    public void setSalary() {
        sendKeysWhenVisible(salaryInput, (num().substring(1, 5)));
    }

    public void setLocation(String loc) {
        sendKeysWhenVisible(locationInput, loc);
    }

    public void setQualifications(String qualification) {
        selectByVisibleText(qualificationsDropdown, qualification);
    }

    public void setGender(String gender1) {
        selectByVisibleText(genderDropdown, gender1);
    }

    public void setBloodGroup(String bg) {
        selectByVisibleText(bloodGroupDropdown, bg);
    }

    public void setReportingTo(String reportingName) {
        visibilityOfElement(reportingToDropdown, 10L);
        selectByValue(reportingToDropdown, reportingName);
    }

    public void setDob(String dobvalue) {

        sendKeysWhenVisible(dateOfBirthWidget, dobvalue);
    }

    public void setJod(String jodvalue) {
        sendKeysWhenVisible(joiningDateWidget, jodvalue);
    }

    public void clickAddBtn() {
        click(addBtn);
    }

    public void setRole(String role) {
        selectByVisibleText(roleDropdown, role);

    }

    public boolean checkAlertMsg() {
        try {
            visibilityOfElement(alertMsg, 2L);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}




