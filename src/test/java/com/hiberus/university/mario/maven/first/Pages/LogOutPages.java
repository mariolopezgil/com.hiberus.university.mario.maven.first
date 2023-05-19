package com.hiberus.university.mario.maven.first.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@Slf4j
public class LogOutPages extends AbstractPage {
    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement menu;
    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    private WebElement logOut;

    public static final String PAGE_URL = "https://www.saucedemo.com/";

    LogOutPages(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public void clickMenu() {
            menu.click();
    }
    public void clickLogOut() throws InterruptedException {
        Thread.sleep(500);
        logOut.click();
    }
}
