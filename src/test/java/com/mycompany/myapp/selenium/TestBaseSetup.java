package com.mycompany.myapp.selenium;

import com.mycompany.myapp.FauxshopApp;
import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.SpringApplication;

import java.util.concurrent.TimeUnit;

public class TestBaseSetup {

    static String LOCALHOST_PREFIX = "http://localhost:8080";
    private static WebDriver driver;

    // Before our tests start we open a new browser
    @BeforeClass
    public static void openBrowser(){
        // The browser can be passed in as a parameter. By default it is set to Chrome.
        // Note how you can run the test with different properties: mvn test -Dselenium.browser=firefox
        System.setProperty("webdriver.chrome.driver", "/Users/derekzuk/tools/chromedriver");
        initializeTestBaseSetup(System.getProperty("selenium.browser", "chrome"));
    }

    // After our Tests are completed we close our browser
    @AfterClass
    public static void closeBrowser(){
        getDriver().quit();
    }

    // This method takes in the browser type and sets the correct driver
    public static void initializeTestBaseSetup(String browserType) {
        try {
            setDriver(browserType);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    // Depending on the browserType passed in, we initialize the corresponding driver. Default is Chrome.
    private static void setDriver(String browserType) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            default:
                System.out.println("browser : " + browserType
                    + " is invalid, Launching Chrome by default");
                driver = initChromeDriver();
        }
    }

    private static void setWait() {
        // An implicit wait means the driver will wait this much time while it is waiting for an element to load:
        driver.manage().timeouts().implicitlyWait(10,  TimeUnit.SECONDS);
    }

    private static WebDriver initChromeDriver() {
        driver = new ChromeDriver();
        setWait();
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        driver = new FirefoxDriver();
        setWait();
        return driver;
    }

    // This method opens the full url based on the partial URL string that is passed in:
    public void openUrl(WebDriver driver, String partialurl) {
        driver.get(getPrefix() + partialurl);
    }

    private String getPrefix() {
        // This checks for a base URL if one is provided. If one is not provided we use the default base URL "http://localhost:9000":
        // We then replace the base URL with the canonical hostname. This allows us to run the tests on environments other than our localhost:
        // Note that you can run the test with different properties: mvn test -Dselenium.base.url=http://www.google.com
        return StringUtils.replace(System.getProperty("selenium.base.url", LOCALHOST_PREFIX), "@localhost@", getCanonicalHostName());
    }
    private String getCanonicalHostName() {
        try {
            return java.net.InetAddress.getLocalHost().getCanonicalHostName();
        } catch (Exception e) {
            return "127.0.0.1";
        }
    }
}
