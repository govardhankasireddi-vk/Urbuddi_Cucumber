package Basepack;

import Utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final boolean useGrid = Boolean.parseBoolean(System.getProperty("useGrid", "false"));
    private static final String gridUrl = System.getProperty("gridUrl", "http://localhost:4444/wd/hub");


    public static void setDriver(String browser) throws MalformedURLException {

        boolean headless = Boolean.parseBoolean(System.getProperty("headless",
                ConfigReader.getProperty("headless")));

        if (useGrid) {
            // Running on Selenium Grid
            DesiredCapabilities capabilities = new DesiredCapabilities();
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) chromeOptions.addArguments("--headless=new");
                    chromeOptions.setExperimentalOption("prefs", buildPrefs());
                    capabilities.merge(chromeOptions);
                    capabilities.setBrowserName("chrome");
                    break;

                case "firefox":
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("permissions.default.desktop-notification", 1);
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) firefoxOptions.addArguments("-headless");
                    firefoxOptions.setProfile(profile);
                    capabilities.merge(firefoxOptions);
                    capabilities.setBrowserName("firefox");
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) edgeOptions.addArguments("--headless=new");
                    edgeOptions.setExperimentalOption("prefs", buildPrefs());
                    capabilities.merge(edgeOptions);
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.set(new RemoteWebDriver(new URL(gridUrl), capabilities));
            getDriver().manage().window().maximize();
        }
        else {

            switch (browser.toLowerCase()) {

                case "chrome":

                    ChromeOptions options = new ChromeOptions();
                    if (headless){ options.addArguments("--headless=new");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--disable-gpu");
                    };
                    options.setExperimentalOption("prefs", buildPrefs());
                    driver.set(new ChromeDriver(options));
                    getDriver().manage().window().maximize();
                    break;

                case "firefox":
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("permissions.default.desktop-notification", 1);
                    FirefoxOptions optionsfox = new FirefoxOptions();
                    if (headless) optionsfox.addArguments("-headless");
                    optionsfox.setProfile(profile);
                    driver.set(new FirefoxDriver(optionsfox));
                    getDriver().manage().window().maximize();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) edgeOptions.addArguments("--headless=new");
                    edgeOptions.setExperimentalOption("prefs", buildPrefs());
                    driver.set(new EdgeDriver(edgeOptions));
                    getDriver().manage().window().maximize();
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

    }

    private static Map<String, Object> buildPrefs() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        return prefs;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().quit();

    }
}

