package com.hiberus.university.mario.maven.first.LogOut;

import com.hiberus.university.mario.maven.first.Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class LogOutTest {
    String url = "https://www.saucedemo.com/";
    WebDriver driver;

    LogOutPages logOutPages;
    LoginPage loginPage;
    String user = "standard_user";
    String password = "secret_sauce";

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        PageFactory.start(driver);
        driver.get(InventarioPages.PAGE_URL);
        PageFactory pagesFactory = PageFactory.getInstance();
        logOutPages = pagesFactory.getLogOutPages();
        loginPage = pagesFactory.getLoginPage();
        loginPage.login(user, password);


    }

    @Test
    public void comprobarLogOut() {

        Assert.assertEquals("El login es incorrecto", LoginPage.PAGE_URL, logOutPages.comprobarLogOut());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
