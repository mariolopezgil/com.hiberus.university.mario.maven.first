package com.hiberus.university.mario.maven.first.CheckOut;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.hiberus.university.mario.maven.first.CheckOut.CheckoutPages;
import com.hiberus.university.mario.maven.first.Login.LoginPages;

public class CheckoutTest {
    String url = "https://www.saucedemo.com/";
    WebDriver driver;
    LoginPages loginPages;

    CheckoutPages checkoutPages;
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

        loginPages = new LoginPages(driver);
        loginPages.login(user, password);
    }

    @Test
    public void comprobarPrecioFinal() {
        checkoutPages = new CheckoutPages(driver);
        checkoutPages.verificarPrecioPedido();
    }

    @Test
    public void RealizarPedido() {
        checkoutPages = new CheckoutPages(driver);
        checkoutPages.VerificarMensajePedido();

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
