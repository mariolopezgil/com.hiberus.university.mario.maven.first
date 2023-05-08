package com.hiberus.university.mario.maven.first.CheckOut;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CheckoutPages {
    private WebDriver driver;
    private WebElement listaInventario;
    private List<WebElement> elementos;
    By carrito = By.xpath("//a[@class='shopping_cart_link']");
    By checkout = By.xpath("//button[@data-test='checkout']");

    By nombre=By.xpath("//input[@data-test='firstName']");
    By apellidos=By.xpath("//input[@data-test='lastName']");
    By cod=By.xpath("//input[@data-test='postalCode']");
    By continuar = By.xpath("//input[@data-test='continue']");
    By finalizar = By.xpath("//button[@data-test='finish']");
    By stringPrecioItems = By.xpath("//div[@class='summary_subtotal_label']");
    By mensaje = By.xpath("//div[@class='complete-text']");
private String nombreUser="mario";
private String apellidoUser="lopez";
private String codUser="1";
    public CheckoutPages(WebDriver driver) {
        this.driver = driver;
    }
    public List numerosAleatorios(int cantidad,int tamanio){
        List<Integer> listaNumeros = new ArrayList<>();
        Random random = new Random();

        while (listaNumeros.size() < cantidad) {
            int num = random.nextInt(tamanio) + 1;
            if (!listaNumeros.contains(num)) {
                listaNumeros.add(num);
            }
        }
        return listaNumeros;

    }

    public void verificarPrecioPedido(){
        WebDriverWait wait = new WebDriverWait(this.driver, 5);
        Double PrecioTotal=0.00;
        List<Integer> lista = numerosAleatorios(3, 6);
        for (int i = 0; i < lista.size(); i++) {
            int numero = lista.get(i);
            String xpath2 = "//div[@class='inventory_item'][" + numero + "]//div[@class='inventory_item_price']";
            String formato= driver.findElement(By.xpath(xpath2)).getText().replace("$","");
            Double precio=Double.parseDouble(formato);
            PrecioTotal+=precio;
            By xpath = By.xpath("//div[@class='inventory_item'][" + numero + "]//button[@class='btn btn_primary btn_small btn_inventory']");
            wait.until(ExpectedConditions.presenceOfElementLocated(xpath)).click();

        }
        driver.findElement(carrito).click();
        driver.findElement(checkout).click();
        driver.findElement(nombre).sendKeys(nombreUser);
        driver.findElement(apellidos).sendKeys(apellidoUser);
        driver.findElement(cod).sendKeys(codUser);
        driver.findElement(continuar).click();
        String[] soloPrecio = driver.findElement(stringPrecioItems).getText().split("\\$");
        String numero = soloPrecio[1];
        Double precioItems= Double.parseDouble(numero);
        driver.findElement(finalizar).click();

        Assert.assertEquals("El precio no coincide",PrecioTotal,precioItems );
    }
    public void VerificarMensajePedido(){
        WebDriverWait wait = new WebDriverWait(this.driver, 5);
        List<Integer> lista = numerosAleatorios(1, 6);
        for (int i = 0; i < lista.size(); i++) {
            int numero = lista.get(i);
            By xpath = By.xpath("//div[@class='inventory_item'][" + numero + "]//button[@class='btn btn_primary btn_small btn_inventory']");
            wait.until(ExpectedConditions.presenceOfElementLocated(xpath)).click();

        }
        driver.findElement(carrito).click();
        driver.findElement(checkout).click();
        driver.findElement(nombre).sendKeys(nombreUser);
        driver.findElement(apellidos).sendKeys(apellidoUser);
        driver.findElement(cod).sendKeys(codUser);
        driver.findElement(continuar).click();
        driver.findElement(finalizar).click();

        Assert.assertEquals("el mensaje no es el mismo",driver.findElement(mensaje).getText(),"Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }
}
