package org.anele.pages;

import org.anele.base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class LoginIntoGitHubPage extends DriverFactory {
    //define WebElements locators
    private static final By username = By.id("login_field");
    private static final By password = By.id("password");
    private static final By signIn = By.xpath("//input[@name='commit']");
    private static final By signupHeader =
            By.xpath("//div[@class=\"mb-4 pb-3 border-bottom color-border-muted h4 text-normal text-center\"]//p");

    //define a method to get the login header
    public static boolean loginHeader(String header) {
        return getDriver().findElement(signupHeader).getText().equalsIgnoreCase(header);
    }

    //login into the gitHub login page
    public static void loginIntoGitHub(String usernameValue, String passwordValue) {
        WebElement usernameWebElement = getDriver().findElement(username);
        WebElement passwordWebElement = getDriver().findElement(password);
        //set values
        setupRequiredValue(usernameWebElement, usernameValue);
        setupRequiredValue(passwordWebElement, passwordValue);
        //click on the sign in btn
        clickBtn();
    }

    //define a reusable method to set up required values
    private static void setupRequiredValue(WebElement element, String value) {
        try {

            //get value of the webElement
            String currentElement = element.getAttribute("value");

            if (!currentElement.isEmpty()) {
                element.sendKeys(Keys.BACK_SPACE.toString().repeat(currentElement.length()));
            }

            element.sendKeys(value);

        } catch (Exception e){
            System.out.println("Element not found with provided valiue: "+ e.getMessage());
        }
    }
    //click on the login btn
    private static void clickBtn() {
        try {
            getDriver().findElement(signIn).click();
        } catch (Exception e){
            System.out.println("Button could not be clicked: " + e.getMessage());
        }
    }
}
