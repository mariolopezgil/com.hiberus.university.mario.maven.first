package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.LoginPages;

public class Login {
    String url = "https://www.saucedemo.com/";
    WebDriver driver;
    LoginPages loginPages;
    String user = "standard_user";
    String password = "secret_sauce";

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(url);

    }

    @Test
    public void validationCorrecto() {
        loginPages= new LoginPages(driver);
        loginPages.setUserName(user);
        loginPages.setPassword(password);
        loginPages.clickLogin();

        Assert.assertEquals("El login es incorrecto",url,driver.getCurrentUrl());
    }

    @Test
    public void validationIncorrecto() {
        loginPages= new LoginPages(driver);
        loginPages.setUserName(user);
        loginPages.setPassword(password);
        loginPages.clickLogin();

        Assert.assertEquals("No sale el elemento que contiene el error",loginPages.getLoginError().isDisplayed());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
