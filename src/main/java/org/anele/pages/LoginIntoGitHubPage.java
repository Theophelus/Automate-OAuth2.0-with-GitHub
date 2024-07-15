package org.anele.pages;

import org.anele.basePage.DriverFactory;
import org.anele.helpers.LogHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class LoginIntoGitHubPage extends DriverFactory {
    static LogHelper log = new LogHelper(LoginIntoGitHubPage.class);
    //define WebElements locators
    private static final By username = By.id("login_field");
    private static final By password = By.id("password");
    private static final By signIn = By.xpath("//input[@name='commit']");
    private static final By signupHeader =
            By.xpath("//div[@class=\"mb-4 pb-3 border-bottom color-border-muted h4 text-normal text-center\"]//p");
    private static final By clickAuthBtn = By.xpath("//button[normalize-space()='Authorize Theophelus']");

    //define a method to get the login header
    public static boolean headerText(String header) {
        log.info(header + ":" + " text is validated successfully");
        return getDriver().findElement(signupHeader).getText().contains(header);
    }

    //login into the gitHub login page
    public static void loginIntoGitHub(String usernameValue, String passwordValue) throws InterruptedException {
        Thread.sleep(6000);
        try {
            if (getDriver().findElement(By.xpath("/html/body/div[1]/div[6]/main/div/div[2]/div[1]/div[2]/div[1]/form/div/button[2]")).isDisplayed()) {
                System.out.println("Authorize button to be clicked: " );
                getDriver().findElement(By.xpath("/html/body/div[1]/div[6]/main/div/div[2]/div[1]/div[2]/div[1]/form/div/button[2]")).click();
                Thread.sleep(6000);
            }
        } catch (NoSuchElementException e) {
            log.info("Reauthorization not required: " + e.getMessage());
            try {
                WebElement usernameWebElement = getDriver().findElement(username);
                WebElement passwordWebElement = getDriver().findElement(password);
                //set values
                setupRequiredValue(usernameWebElement, usernameValue);
                setupRequiredValue(passwordWebElement, passwordValue);
                //click on the sign in btn
                clickBtn();

            } catch (NoSuchElementException elementException) {
                log.severe("Login elements not found: " + elementException.getMessage());
            }
        }
    }

    //define a reusable method to set up required values
    private static void setupRequiredValue(WebElement element, String value) {
        try {

            //get value of the webElement
            String currentElement = element.getAttribute("value");

            if (!currentElement.isEmpty()) {
                log.info("Clearing text box for element " + currentElement + " ");
                element.sendKeys(Keys.BACK_SPACE.toString().repeat(currentElement.length()));
            }

            element.sendKeys(value);

        } catch (Exception e) {
            log.error("Element " + element + " not found with provided value", e.getMessage());
        }
    }

    //click on the login btn
    private static void clickBtn() {
        try {
            var element = getDriver().findElement(signIn);

            log.info(getDriver().findElement(signIn).getText() + "button is about to be clicked");

            if (element.isDisplayed()) {

                log.info(getDriver().findElement(signIn).getText() + ":" + "button clicked successfully");
                getDriver().findElement(signIn).click();

            }
        } catch (Exception e) {
            log.error("Button could not be clicked for provided element: ", e.getMessage());
        }
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
