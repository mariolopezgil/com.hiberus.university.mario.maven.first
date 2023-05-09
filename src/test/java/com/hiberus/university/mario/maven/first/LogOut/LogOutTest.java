package com.hiberus.university.mario.maven.first.LogOut;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.hiberus.university.mario.maven.first.Login.LoginPages9;

public class LogOutTest {
    String url = "https://www.saucedemo.com/";
    WebDriver driver;
    LoginPages9 loginPages;
    LogOutPages logOutPages;
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

        loginPages = new LoginPages9(driver);
        loginPages.login(user, password);
    }

    @Test
    public void comprobarLogOut() {
        logOutPages = new LogOutPages(driver);
        logOutPages.comprobarLogOut();

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
