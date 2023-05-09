package com.hiberus.university.mario.maven.first.Pages;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class CheckOutPages extends AbstractPage{
    @FindBy(xpath ="//a[@class='shopping_cart_link']")
    private WebElement carrito;
    @FindBy(xpath ="//a[@class='shopping_cart_link']")
    private WebElement checkOut;
    @FindBy(xpath ="//a[@class='shopping_cart_link']")
    private WebElement nombre;
    @FindBy(xpath ="//a[@class='shopping_cart_link']")
    private WebElement apellidos;
    @FindBy(xpath ="//a[@class='shopping_cart_link']")
    private WebElement cod;
    @FindBy(xpath ="//a[@class='shopping_cart_link']")
    private WebElement finish;
    @FindBy(xpath ="//a[@class='shopping_cart_link']")
    private WebElement continuar;
    @FindBy(xpath ="//div[@class='summary_subtotal_label']")
    private WebElement stringPrecios;
    @FindBy(xpath ="//div[@class='complete-text']")
    private WebElement mensaje;

    public static final String PAGE_URL = "https://www.saucedemo.com/";
    private static final String nombreUser="a";
    private static final String apellidoUser="a";
    private static final String  codUser="1";
    CheckOutPages(WebDriver driver) {
        super(driver);
    }
    public List numerosAleatorios(int cantidad, int tamanio){
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
        log.info("Verificando...");
        try {
            Double PrecioTotal=0.00;
            List<Integer> lista = numerosAleatorios(3, 6);
            for (int i = 0; i < lista.size(); i++) {
                int numero = lista.get(i);
                WebElement productoAleatorio = (WebElement) By.xpath("//div[@class='inventory_item'][" + numero + "]//div[@class='inventory_item_price']");
                String formato= productoAleatorio.getText().replace("$","");
                Double precio=Double.parseDouble(formato);
                PrecioTotal+=precio;
                By xpath = By.xpath("//div[@class='inventory_item'][" + numero + "]//button[@class='btn btn_primary btn_small btn_inventory']");
                wait.until(ExpectedConditions.presenceOfElementLocated(xpath)).click();

            }
            carrito.click();
            checkOut.click();
            nombre.sendKeys(nombreUser);
            apellidos.sendKeys(apellidoUser);
            cod.sendKeys(codUser);
            continuar.click();
            String[] soloPrecio = stringPrecios.getText().split("\\$");
            String numero = soloPrecio[1];
            Double precioItems= Double.parseDouble(numero);
            finish.click();

        } catch (TimeoutException timeoutException) {
            log.info("Timeout clicking login: " + timeoutException.getClass().getSimpleName());
        }

    }
    public void VerificarMensajePedido(){
        log.info("Verificando mensaje de pedido...");
        try {
            List<Integer> lista = numerosAleatorios(1, 6);
            for (int i = 0; i < lista.size(); i++) {
                int numero = lista.get(i);
                By xpath = By.xpath("//div[@class='inventory_item'][" + numero + "]//button[@class='btn btn_primary btn_small btn_inventory']");
                wait.until(ExpectedConditions.presenceOfElementLocated(xpath)).click();

            }
            carrito.click();
            checkOut.click();
            nombre.sendKeys(nombreUser);
            apellidos.sendKeys(apellidoUser);
            cod.sendKeys(codUser);
            continuar.click();
            finish.click();

        } catch (TimeoutException timeoutException) {
            log.info("Timeout verificar pedido: " + timeoutException.getClass().getSimpleName());
        }

    }
}
