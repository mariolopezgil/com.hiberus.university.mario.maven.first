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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InvetarioPages;
import pages.LoginPages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventario {
    String url = "https://www.saucedemo.com/";
    WebDriver driver;
    LoginPages loginPages;

    InvetarioPages invetarioPages;
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
    public void validarNumeroInventario() {
        invetarioPages = new InvetarioPages(driver);
        int numero = invetarioPages.obtenerNumeroElementos(driver);
        Assert.assertEquals("No es igual al esperado", 6, numero);
    }

    @Test
    public void validarExisteProducto() {
        invetarioPages = new InvetarioPages(driver);
        Assert.assertTrue("No existe este producto", invetarioPages.getCamiseta().isDisplayed());
    }

    @Test
    public void AniadeProducto() {
        invetarioPages = new InvetarioPages(driver);
        Assert.assertEquals("El numero de carrito no coincide con el esperado", "1", invetarioPages.obtenerNumeroCarrito());
    }

    @Test
    public void EliminarProducto() {
        invetarioPages = new InvetarioPages(driver);
        invetarioPages.clickAniadirSudaderayEliminarla();
        Assert.assertTrue("No se ha eliminado correctamente", invetarioPages.obtenerNumeroCarrito().isEmpty());
    }

    @Test
    public void Aniadir3Productos() {
        invetarioPages = new InvetarioPages(driver);
        invetarioPages.aniadir3productos();
        Assert.assertEquals("El numero no coincide", "3", invetarioPages.obtenerNumeroCarrito());
    }

    @Test
    public void OrdenAlfabeticoInventario() {
        invetarioPages = new InvetarioPages(driver);
        invetarioPages.ordenAlfabetico();

    }

    @Test
    public void OrdenPrecioInventarioMayorAMenor() {

        invetarioPages = new InvetarioPages(driver);
        invetarioPages.mayorMenor();
    }

    @Test
    public void OrdenPrecioMenorAMayor() {
        invetarioPages = new InvetarioPages(driver);
        invetarioPages.menorMayor();
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
