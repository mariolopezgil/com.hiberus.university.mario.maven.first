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
        loginPages.setUserName(user);
        loginPages.setPassword(password);
        loginPages.clickLogin();
    }

    @Test
    public void validarNumeroInventario() {
        // WebElement listaInventario = driver.findElement(By.xpath("//div[@class='inventory_list']"));

        //List<WebElement> elementos = listaInventario.findElements(By.xpath("//div[@class='inventory_item']"));
        // int numero = elementos.size();
        // Assert.assertEquals("No es igual al esperado",6, numero);

        invetarioPages = new InvetarioPages(driver);
        int numero = invetarioPages.obtenerNumeroElementos(driver);
        Assert.assertEquals("No es igual al esperado", 6, numero);


    }

    @Test
    public void validarExisteProducto() {
        //hacer de dos formas
        //WebElement camiseta = driver.findElement(By.xpath("//div[@class='inventory_item_name' and contains(text(), 'Sauce Labs Bolt T-Shirt')]"));
        invetarioPages = new InvetarioPages(driver);
        Assert.assertTrue("No existe este producto", invetarioPages.getCamiseta().isDisplayed());

    }

    @Test
    public void AniadeProducto() {
        // WebElement add_btn = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']"));
        //add_btn.click();
        //WebElement numero_carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        //String numero = numero_carrito.getText();
        invetarioPages = new InvetarioPages(driver);
        Assert.assertEquals("El numero de carrito no coincide con el esperado", "1", invetarioPages.obtenerNumeroCarrito());

    }

    @Test
    public void EliminarProducto() {
        WebElement add_btn = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-onesie']"));
        add_btn.click();

        WebElement remove_btn = driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-onesie']"));
        remove_btn.click();

        WebElement numeroCarrito = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));

        Assert.assertTrue("No se ha eliminado correctamente", numeroCarrito.getText().isEmpty());


    }

    @Test
    public void Aniadir3Productos() {
        WebElement containerDiv = driver.findElement(By.xpath("//div[@class='inventory_list']"));
        int tamanio = containerDiv.findElements(By.xpath("//div[@class='inventory_item']")).size();
        int num1 = (int) (Math.random() * (tamanio - 1 + 1)) + 1;
        int num2 = (int) (Math.random() * (tamanio - 1 + 1)) + 1;
        while (num2 == num1) {
            num2 = (int) (Math.random() * (tamanio - 1 + 1)) + 1;
        }
        int num3 = (int) (Math.random() * (tamanio - 1 + 1)) + 1;
        while (num3 == num1 || num3 == num2) {
            num3 = (int) (Math.random() * (tamanio - 1 + 1)) + 1;
        }

        WebElement elemento1 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num1 + "]//button[@class='btn btn_primary btn_small btn_inventory']"));
        elemento1.click();
        WebElement elemento2 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num2 + "]//button[@class='btn btn_primary btn_small btn_inventory']"));
        elemento2.click();
        WebElement elemento3 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num3 + "]//button[@class='btn btn_primary btn_small btn_inventory']"));
        elemento3.click();

        WebElement numeroCarrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));

        String numero = numeroCarrito.getText();
        Assert.assertEquals("El numero no coincide", "3", numero);
    }

    @Test
    public void OrdenAlfabeticoInventario() {

        List<WebElement> elementos = driver.findElements(By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_name']"));
        List<String> nombres = new ArrayList<>();

// Obtener los nombres de los elementos y almacenarlos en la lista "nombres"
        for (WebElement elemento : elementos) {
            nombres.add(elemento.getText());
        }

// Ordenar la lista "nombres" en orden ascendente
        Collections.sort(nombres);

        List<String> nombresEsperados = new ArrayList<String>();
        nombresEsperados.add("Sauce Labs Backpack");
        nombresEsperados.add("Sauce Labs Bike Light");
        nombresEsperados.add("Sauce Labs Bolt T-Shirt");
        nombresEsperados.add("Sauce Labs Fleece Jacket");
        nombresEsperados.add("Sauce Labs Onesie");
        nombresEsperados.add("Test.allTheThings() T-Shirt (Red)");

        Assert.assertEquals("El resultado de las listas ordenadas alfabeticamente no son iguales", nombresEsperados, nombres);


    }

    @Test
    public void OrdenPrecioInventarioMayorAMenor() {
        driver.findElement(By.xpath("//select[@data-test='product_sort_container']//option[@value='hilo']")).click();
        List<WebElement> elementos = driver.findElements(By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_price']"));
        List<Double> precio = new ArrayList<>();

// Obtener los nombres de los elementos y almacenarlos en la lista "nombres"
        for (WebElement elemento : elementos) {
            String nombre = elemento.getText();
            String precioElementos = nombre.replace("$", "");
            Double precioDouble = Double.parseDouble(precioElementos);
            precio.add(precioDouble);

        }

        Collections.sort(precio, Collections.reverseOrder());
        System.out.println(precio);

        List<Double> precioEsperado = new ArrayList<>();

        precioEsperado.add(49.99);
        precioEsperado.add(29.99);
        precioEsperado.add(15.99);
        precioEsperado.add(15.99);
        precioEsperado.add(9.99);
        precioEsperado.add(7.99);


        Assert.assertEquals("El resultado de las listas ordenadas por precio de mayor a menor no son iguales", precioEsperado, precio);


    }

    @Test
    public void OrdenPrecioMenorAMayor() {
        driver.findElement(By.xpath("//select[@data-test='product_sort_container']//option[@value='lohi']")).click();
        List<WebElement> elementos = driver.findElements(By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_price']"));
        List<Double> precio = new ArrayList<>();

// Obtener los nombres de los elementos y almacenarlos en la lista "nombres"
        for (WebElement elemento : elementos) {
            String nombre = elemento.getText();
            String precioElementos = nombre.replace("$", "");
            Double precioDouble = Double.parseDouble(precioElementos);
            precio.add(precioDouble);

        }


        driver.findElement(By.xpath("//select[@data-test='product_sort_container']//option[@value='az']")).click();
        List<WebElement> elementosSinOrdenar = driver.findElements(By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_price']"));
        List<Double> precioEsperado = new ArrayList<>();
        for (WebElement elemento : elementosSinOrdenar) {
            String nombre2 = elemento.getText();
            String precioElementos2 = nombre2.replace("$", "");
            Double precioDouble2 = Double.parseDouble(precioElementos2);
            precio.add(precioDouble2);

        }
        Collections.sort(precioEsperado);
        System.out.println(precioEsperado);

        Assert.assertEquals("El resultado de las listas ordenadas de menor a mayor no son iguales", precio, precioEsperado);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
