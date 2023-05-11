package com.hiberus.university.mario.maven.first.Test;

import com.hiberus.university.mario.maven.first.Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class CheckoutTest {
    WebDriver driver;

    CheckOutPages checkOutPages;
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
        driver.get(CarritoPages.PAGE_URL);
        PageFactory pagesFactory = PageFactory.getInstance();
        checkOutPages = pagesFactory.getCheckOutPages();
        loginPage = pagesFactory.getLoginPage();
        loginPage.login(user, password);
    }

    @Test
    public void comprobarPrecioFinal() {
        checkOutPages.verificarPrecioPedido();
    }

    @Test
    public void RealizarPedido() {
        Assert.assertEquals("No es el mismo mensaje", "Your order has been dispatched, and will arrive just as fast as the pony can get there!", checkOutPages.VerificarMensajePedido());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
