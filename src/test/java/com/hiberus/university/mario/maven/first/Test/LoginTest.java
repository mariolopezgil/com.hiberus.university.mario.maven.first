package com.hiberus.university.mario.maven.first.Test;

import com.hiberus.university.mario.maven.first.Pages.InventarioPages;
import com.hiberus.university.mario.maven.first.Pages.LoginPage;
import com.hiberus.university.mario.maven.first.Pages.PageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class LoginTest {
    WebDriver driver;
    public LoginPage loginPage;
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
        driver.get(LoginPage.PAGE_URL);
        PageFactory pagesFactory = PageFactory.getInstance();
        loginPage = pagesFactory.getLoginPage();


    }

    @Test
    public void validationCorrecto() {


        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();


        Assert.assertEquals("El login es incorrecto", InventarioPages.PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    public void validationIncorrecto() {
        driver.get(LoginPage.PAGE_URL);
        PageFactory pagesFactory = PageFactory.getInstance();
        LoginPage loginPage = pagesFactory.getLoginPage();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secretsauce");
        loginPage.clickLogin();
        loginPage.hasUsernamePasswordError();

        Assert.assertTrue("No sale el elemento que contiene el error", loginPage.hasUsernamePasswordError());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
