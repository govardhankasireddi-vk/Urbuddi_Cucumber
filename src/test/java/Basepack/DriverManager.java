package Basepack;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    static Map<String, Object> prefs = new HashMap<>();


    public static void setDriver(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                prefs.put("profile.default_content_setting_values.notifications", 1);
                options.setExperimentalOption("prefs", prefs);
                driver.set(new ChromeDriver(options));
                getDriver().manage().window().maximize();
                break;

            case "firefox":
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("permissions.default.desktop-notification", 1);
                FirefoxOptions optionsfox = new FirefoxOptions();
                optionsfox.setProfile(profile);
                driver.set(new FirefoxDriver(optionsfox));
                getDriver().manage().window().maximize();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                prefs.put("profile.default_content_setting_values.notifications", 1);
                edgeOptions.setExperimentalOption("prefs", prefs);
                driver.set(new EdgeDriver(edgeOptions));
                getDriver().manage().window().maximize();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        System.out.println("Running scenario on thread: " + Thread.currentThread().getId());

    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().quit();

    }
}
