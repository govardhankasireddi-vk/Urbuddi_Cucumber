package pom;

import Utilities.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DeleteEmployee_Page extends ElementUtils {
    WebElement item;
    public DeleteEmployee_Page(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @FindBy(xpath = "//div[@class='ag-selection-checkbox']")
    List<WebElement> checkBox;

    @FindBy(xpath = "//div[@role ='row'][@row-index='0']//div[@aria-colindex='6']")
    WebElement initialemployee;


    By actualemployee = By.xpath("//div[@role ='row'][@row-index='0']//div[@aria-colindex='6']");

    //div[@class='ag-selection-checkbox']//input[@type='checkbox']
    //div[@role ='row'][@row-index='0']//div[@aria-colindex='6']



    @FindBy(xpath = "//input[contains(@aria-label,'NAME')]")
    WebElement nameFilterInput;

    @FindBy(xpath = "//span[contains(@class,'ag-icon ag-icon-next')]")
    WebElement nextPageBtn;

    @FindBy(xpath = "//span[@id='ag-3-of-page-number']")
    WebElement PageNo;



    @FindBy(xpath = "//button[@class = 'deleteIcon']")
    WebElement deleteBtn;

    public void setName(String name) {
        sendKeysWhenVisible (nameFilterInput,name);
    }

    public int getCheckBox() {
        return checkBox.size();
    }


    public int getMaxPageNo() {
        return Integer.parseInt(getText(PageNo));
    }

    public void deleteEmployee() throws InterruptedException {

        for (int i = 0; i < getCheckBox(); i++) {
            item = checkBox.get(i);
           click(item);
           System.out.println(i);
        }
    }
    public String getInitialEmployee() {

        return getText(initialemployee);

    }
    public boolean employeesfilitered() {
        try {
            textNotToBePresentwait(actualemployee, getInitialEmployee());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void clickNextPageBtn(){
        click(nextPageBtn);
    }

    public void clickDeleteBtn(){
       // ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        click(deleteBtn);
    }
}
