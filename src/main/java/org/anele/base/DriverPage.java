package org.anele.base;

import org.openqa.selenium.WebDriver;

public class DriverPage {

    //define a WebDrivers to make my driver thread safe
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private DriverPage(){

    }

    public static void setDriver(WebDriver webDriver){
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
