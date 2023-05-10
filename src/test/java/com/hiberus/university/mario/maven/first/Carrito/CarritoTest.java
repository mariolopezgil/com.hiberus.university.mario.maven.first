package com.hiberus.university.mario.maven.first.Carrito;

import com.hiberus.university.mario.maven.first.Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class CarritoTest {

    WebDriver driver;

    CarritoPages carritoPages;
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
        driver.get(LoginPage.PAGE_URL);
        PageFactory pagesFactory = PageFactory.getInstance();
        carritoPages = pagesFactory.getCarritoPages();
        loginPage = pagesFactory.getLoginPage();
        loginPage.login(user, password);


    }

    @Test
    public void eliminarProductoCarrito() {
        carritoPages.aniadir2productos();
        Assert.assertEquals("El numero de carrito no coincide con el esperado", "1", carritoPages.eliminarProductoCarrito());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
