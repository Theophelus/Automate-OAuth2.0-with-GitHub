package org.anele.basePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.anele.helpers.LogHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ThreadGuard;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DriverFactory {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    static LogHelper log = new LogHelper(DriverFactory.class);

    //get The driver from DriverPage
    public static WebDriver getDriver() {
        return driver.get();
    }

    //add a method to getCurrentBrowser with one parameter
    public static void getCurrentBrowser(String browser) {
        initializeDrivers(browser);
    }

    //Define a method to initializeDrivers
    static void initializeDrivers(String browser) {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        EdgeOptions edgeOptions = getEdgeOptions(capabilities);

        if (browser != null && browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            //make sure WebDriver is only called by the thread that created it using ThreadGuard.protect()
            driver.set(ThreadGuard.protect(new EdgeDriver(edgeOptions)));
        }
        maximizeBrowser(getDriver());
    }

    //getChromeOptions
    static EdgeOptions getEdgeOptions(DesiredCapabilities capabilities) {
        EdgeOptions options = new EdgeOptions();
        List<String> argumentList = new ArrayList<>();
        argumentList.add("--disable-gbu");
        argumentList.add("--no-sandbox");
        argumentList.add("--headless=new");
        options.addArguments(argumentList);
        capabilities.setCapability(EdgeOptions.CAPABILITY, options);
        options.merge(capabilities);
        return options;
    }

    static void maximizeBrowser(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();
    }

    //quit drivers
    public static void quitDrivers() {
        getDriver().quit();
        driver.remove();
        //add logHelper
        log.endTest();
    }


}
