package com.mycompany.myapp.selenium;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageObject {

    private WebDriver driver;
    private static WebElement element = null;

    private static By loginTitle = By.id("loginTitle");

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement loginTitle(WebDriver driver){
        element = driver.findElement(loginTitle);
        return element;
    }

    public boolean verifyLoginTitle() {
        String loginTitleText = "Sign in";
        return loginTitle(driver).getText().contains(loginTitleText);
    }
}
