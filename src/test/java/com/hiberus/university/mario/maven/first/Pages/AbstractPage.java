package com.hiberus.university.mario.maven.first.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public abstract class AbstractPage {
    private final WebDriver driver;

    protected Wait<WebDriver> wait;

    AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10, 500);
    }

    public abstract WebElement getPageLoadedTestElement();

    protected WebDriver getDriver() {
        return driver;
    }

    protected boolean isPageLoaded(WebElement element) {
        boolean isLoaded = false;
        try {
            isLoaded = element.isDisplayed();
        } catch (NoSuchElementException elementException) {
            elementException.printStackTrace();
        }
        return isLoaded;
    }
}
