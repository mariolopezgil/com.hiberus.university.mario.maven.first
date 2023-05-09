package com.hiberus.university.mario.maven.first.Pages;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
@Slf4j
public class LogOut extends AbstractPage{
    @FindBy(xpath ="//button[@id='react-burger-menu-btn']" )
    private WebElement menu;
    @FindBy(xpath ="//a[@id='logout_sidebar_link']" )
    private WebElement logOut;

    public static final String PAGE_URL = "https://www.saucedemo.com/";
    LogOut(WebDriver driver) {
        super(driver);
    }
    public void comprobarLogOut() {
        log.info("LogOut in...");
        try {
            menu.click();
            logOut.click();

        } catch (TimeoutException timeoutException) {
            log.info("Timeout clicking logOut: " + timeoutException.getClass().getSimpleName());
        }
    }
}
