package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.CarritoPages;
import pages.InvetarioPages;
import pages.LoginPages;

import java.util.List;

public class Carrito {
    String url = "https://www.saucedemo.com/";
    WebDriver driver;
    CarritoPages carritopages;
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

        loginPages = new LoginPages(driver);
        loginPages.login(user, password);

    }

    @Test
    public void eliminarProductoCarrito() {
        carritopages = new CarritoPages(driver);
        carritopages.eliminarProductoCarrito();

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
