package JUnit;

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

import java.util.List;

public class Carrito {
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
    public void eliminarProductoCarrito(){
        WebElement containerDiv = driver.findElement(By.xpath("//div[@class='inventory_list']"));
        int tamanio = containerDiv.findElements(By.xpath("//div[@class='inventory_item']")).size();
        int num1 = (int)(Math.random() * (tamanio - 1 + 1)) + 1;
        int num2 = (int)(Math.random() * (tamanio - 1 + 1)) + 1;
        while (num2 == num1) {
            num2 = (int)(Math.random() * (tamanio- 1 + 1)) + 1;
        }
        WebElement elemento1 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num1 + "]//button[@class='btn btn_primary btn_small btn_inventory']"));
        elemento1.click();
        WebElement elemento2 = containerDiv.findElement(By.xpath("//div[@class='inventory_item'][" + num2 + "]//button[@class='btn btn_primary btn_small btn_inventory']"));
        elemento2.click();

        WebElement carrito = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        carrito.click();

        int num3 = (int)(Math.random() * (2 - 1 + 1)) + 1;
        WebElement eliminar = driver.findElement(By.xpath("//div[@class='cart_list'][" + num3 + "]//button[@class='btn btn_secondary btn_small cart_button']"));
        eliminar.click();

        WebElement numeroCarrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        Assert.assertEquals( numeroCarrito.getText(), "1");

    }
    @After
    public void tearDown(){
        driver.close();
    }
}
