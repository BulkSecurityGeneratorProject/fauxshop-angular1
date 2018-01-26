package com.mycompany.myapp.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObject {

    private WebDriver driver;
    private static WebElement element;

    // We list how we will retrieve our page elements here
    private static By mainHeader = By.id("mainHeader");
    private static By loginId = By.id("login");

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    // We return each page element individually as part of our Page Object class
    public WebElement mainHeader(WebDriver driver){
        element = driver.findElement(mainHeader);
        return element;
    }

    public WebElement homeLoginId(WebDriver driver){
        element = driver.findElement(loginId);
        return element;
    }

    // We return our verification outside of our Test class:
    public boolean verifyMainHeader() {
        String pageTitle = "STELLA";
        return mainHeader(driver).getText().contains(pageTitle);
    }

}
