package Utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementUtils {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void visibilityOfElement(WebElement element, Long sec) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void textNotToBePresentwait(By element, String text) {
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElementLocated(element, text)));
    }

    public void invisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void elementClickable(WebElement element, Long sec) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void sendKeysWhenVisible(WebElement element, String value) {
        visibilityOfElement(element, 10L);
        element.clear();
        element.sendKeys(value);
    }

    public void selectByVisibleText(WebElement element, String visibleText) {
        visibilityOfElement(element, 5L);
        Select select = new Select(element);
        select.selectByVisibleText(visibleText.trim());
    }

    public void selectByValue(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        wait.until(driver -> {
            return select.getOptions().size() > 1;
        });
        select.selectByValue(value);
    }

    public void click(WebElement element) {
        visibilityOfElement(element, 15L);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
        elementClickable(element,5L);
        element.click();
    }

    public String getText(WebElement element) {
        visibilityOfElement(element, 15L);
        return element.getText();
    }

}

