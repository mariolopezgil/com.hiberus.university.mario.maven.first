package com.hiberus.university.mario.maven.first.Inventario;

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

public class InventarioTest {
    WebDriver driver;
    String user = "standard_user";
    String password = "secret_sauce";
    InventarioPages inventarioPages;
    LoginPage loginPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        PageFactory.start(driver);
        PageFactory pagesFactory = PageFactory.getInstance();
        loginPage = pagesFactory.getLoginPage();
        driver.get(LoginPage.PAGE_URL);
        loginPage.login(user, password);
        inventarioPages = pagesFactory.getInventarioPages();


    }

    @Test
    public void validarNumeroInventario() throws InterruptedException {
        ;
        Assert.assertEquals("No es igual al esperado", 6, inventarioPages.obtenerNumeroElementos());
    }

    @Test
    public void validarExisteProducto() {

        Assert.assertTrue("No existe este producto", inventarioPages.getCamiseta());
    }

    @Test
    public void AniadeProducto() throws InterruptedException {
        inventarioPages.clickAniadir();

        Assert.assertEquals("El numero de carrito no coincide con el esperado", "1", inventarioPages.obtenerNumeroCarrito());
    }

    @Test
    public void EliminarProducto() throws InterruptedException {
        inventarioPages.clickAniadirSudaderayEliminarla();
        Assert.assertTrue("El numero de carrito no coincide con el esperado", inventarioPages.addSauceLabsEnabled());
    }

    @Test
    public void Aniadir3Productos() {
        inventarioPages.aniadir3productos();

        Assert.assertEquals("El numero no coincide", "3", inventarioPages.obtenerNumeroCarrito());
    }

    @Test
    public void OrdenAlfabeticoInventario() {
        inventarioPages.ordenAlfabetico();

    }

    @Test
    public void OrdenPrecioInventarioMayorAMenor() {
        inventarioPages.mayorMenor();

    }

    @Test
    public void OrdenPrecioMenorAMayor() {
        inventarioPages.menorMayor();

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
