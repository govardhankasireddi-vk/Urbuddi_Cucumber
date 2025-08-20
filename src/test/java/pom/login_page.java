package pom;

import Utilities.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class login_page extends ElementUtils {


    @FindBy(id = "userEmail")
    WebElement emailTxt;

    @FindBy(id = "userPassword")
    WebElement passwordTxt;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement loginBtn;


    public login_page(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void enterEmail(String email) {
        sendKeysWhenVisible(emailTxt, email);
    }

    public void enterPassword(String password) {
        sendKeysWhenVisible(passwordTxt, password);
    }

    public void clickOnloginBtn() {
        click(loginBtn);
    }

}
