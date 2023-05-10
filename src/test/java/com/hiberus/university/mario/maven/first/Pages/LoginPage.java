package com.hiberus.university.mario.maven.first.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public
class LoginPage extends AbstractPage {
    public static final String PAGE_URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }


    public void clickLogin() {
        log.info("Logging in...");
        try {
            loginButton.click();
        } catch (TimeoutException timeoutException) {
            log.info("Timeout login: " + timeoutException.getClass().getSimpleName());
        }
    }

    public void enterUsername(String username) {

        log.info("Rellenando usuario...");
        try {
            usernameInput.click();
            usernameInput.sendKeys(username);
        } catch (TimeoutException timeoutException) {
            log.info("Timeout clicking login: " + timeoutException.getClass().getSimpleName());
        }
    }

    public void enterPassword(String password) {

        log.info("Introduciendo contraseña...");
        try {

            passwordInput.click();
            passwordInput.sendKeys(password);
        } catch (TimeoutException timeoutException) {
            log.info("Timeout login: " + timeoutException.getClass().getSimpleName());
        }
    }
    public void login(String username,String password) {

        log.info("Introduciendo contraseña...");
        try {
            usernameInput.click();
            usernameInput.sendKeys(username);
            passwordInput.click();
            passwordInput.sendKeys(password);
            loginButton.click();
        } catch (TimeoutException timeoutException) {
            log.info("Timeout login: " + timeoutException.getClass().getSimpleName());
        }

    }

    public boolean hasUsernamePasswordError() {
        return errorMessage.isDisplayed();
    }
}

