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

public class Checkout {
    String url="https://www.saucedemo.com/";
    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options= new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(url);

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");


        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");


        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();
    }
    @Test
    public void comprobarPrecioFinal(){
        WebElement containerDiv = driver.findElement(By.xpath("//div[@class='inventory_list']"));
        int tamanio = containerDiv.findElements(By.xpath("//div[@class='inventory_item']")).size();
        int num1 = (int)(Math.random() * (tamanio - 1 + 1)) + 1;
        int num2 = (int)(Math.random() * (tamanio - 1 + 1)) + 1;
        while (num2 == num1) {
            num2 = (int)(Math.random() * (tamanio- 1 + 1)) + 1;
        }
        int num3 = (int)(Math.random() * (tamanio - 1 + 1)) + 1;
        while (num3 == num1 || num1==num2) {
            num3 = (int)(Math.random() * (tamanio- 1 + 1)) + 1;
        }

        WebElement precioElemento1 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num1 + "]//div[@class='inventory_item_price']"));
        String p1=precioElemento1.getText();
        String formato= p1.replace("$","");
        Double Precio1=Double.parseDouble(formato);

        WebElement precioElemento2 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num2 + "]//div[@class='inventory_item_price']"));
        String p2=precioElemento2.getText();
        String formato2= p2.replace("$","");
        Double Precio2=Double.parseDouble(formato2);

        WebElement precioElemnto3 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num3 + "]//div[@class='inventory_item_price']"));
        String p3=precioElemnto3.getText();
        String formato3= p3.replace("$","");
        Double Precio3=Double.parseDouble(formato3);

        Double precioTotalProductos= Precio1+Precio2+Precio3;

        WebElement elemento1 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num1 + "]//button[@class='btn btn_primary btn_small btn_inventory']"));
        elemento1.click();
        WebElement elemento2 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num2 + "]//button[@class='btn btn_primary btn_small btn_inventory']"));
        elemento2.click();
        WebElement elemento3 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num3 + "]//button[@class='btn btn_primary btn_small btn_inventory']"));
        elemento3.click();

        WebElement carrito = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        carrito.click();

        WebElement checkout = driver.findElement(By.xpath("//button[@data-test='checkout']"));
        checkout.click();

        WebElement nombre = driver.findElement(By.xpath("//input[@data-test='firstName']"));
        nombre.sendKeys("mario");

        WebElement apellidos = driver.findElement(By.xpath("//input[@data-test='lastName']"));
        apellidos.sendKeys("lopez");

        WebElement codigoPostal = driver.findElement(By.xpath("//input[@data-test='postalCode']"));
        codigoPostal.sendKeys("50014");

        WebElement continuar = driver.findElement(By.xpath("//input[@data-test='continue']"));
        continuar.click();

        WebElement stringPrecioItems = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        stringPrecioItems.getText();
        String[] soloPrecio = stringPrecioItems.getText().split("\\$");
        String numero = soloPrecio[1];
        Double precioItems= Double.parseDouble(numero);

        WebElement finalizar = driver.findElement(By.xpath("//button[@data-test='finish']"));
        finalizar.click();

        Assert.assertEquals("El precio no coincide",precioTotalProductos,precioItems );
        System.out.println(precioItems);
        System.out.println(precioTotalProductos);

    }
    @Test
    public void RealizarPedido(){
        WebElement containerDiv = driver.findElement(By.xpath("//div[@class='inventory_list']"));
        int tamanio = containerDiv.findElements(By.xpath("//div[@class='inventory_item']")).size();
        int num1 = (int)(Math.random() * (tamanio - 1 + 1)) + 1;

        WebElement elemento1 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num1 + "]//button[@class='btn btn_primary btn_small btn_inventory']"));
        elemento1.click();

        WebElement carrito = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        carrito.click();

        WebElement checkout = driver.findElement(By.xpath("//button[@data-test='checkout']"));
        checkout.click();

        WebElement nombre = driver.findElement(By.xpath("//input[@data-test='firstName']"));
        nombre.sendKeys("mario");

        WebElement apellidos = driver.findElement(By.xpath("//input[@data-test='lastName']"));
        apellidos.sendKeys("lopez");

        WebElement codigoPostal = driver.findElement(By.xpath("//input[@data-test='postalCode']"));
        codigoPostal.sendKeys("50014");

        WebElement continuar = driver.findElement(By.xpath("//input[@data-test='continue']"));
        continuar.click();

        WebElement finalizar = driver.findElement(By.xpath("//button[@data-test='finish']"));
        finalizar.click();

        WebElement mensaje = driver.findElement(By.xpath("//div[@class='complete-text']"));

        Assert.assertEquals("El mensaje de texto no es el correcto",mensaje.getText(),"Your order has been dispatched, and will arrive just as fast as the pony can get there!");

    }

    @After
    public void tearDown(){
        driver.close();
    }
}
