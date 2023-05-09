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
public class CarritoPages extends AbstractPage{
    int pos;
    @FindBy(xpath ="//div[@class='inventory_list']" )
    private List <WebElement> listaCarrito;
    @FindBy(xpath ="//div[@class='inventory_item']" )
    private List <WebElement> itemList;
    @FindBy(xpath ="//a[@class='shopping_cart_link']")
    private WebElement carrito;
    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    private WebElement numeroCarrito;
    @FindBy(xpath = "//button[@class='btn btn_secondary btn_small cart_button']")
    private List <WebElement> lisatRemove;

    public static final String PAGE_URL = "https://www.saucedemo.com/";
    CarritoPages(WebDriver driver) {
        super(driver);
    }
    public void eliminarProductoCarrito(List <WebElement> a) {
        log.info("Eliminando un producto random...");
        try {
            a=lisatRemove;
            List<Integer> lista = numerosAleatorios(1, listaCarrito.size());
            carrito.click();
            for (int i = 0; i < lista.size(); i++) {
                int numero = lista.get(i);
                a.get(numero).click();

            }

        } catch (TimeoutException timeoutException) {
            log.info("Timeout eliminar 1 producto aleatorio: " + timeoutException.getClass().getSimpleName());
        }


    }

    public List numerosAleatorios(int cantidad, int tamanio) {
        List<Integer> listaNumeros = new ArrayList<>();
        Random random = new Random();

        while (listaNumeros.size() < cantidad) {
            int num = random.nextInt(listaCarrito.size()) + 1;
            if (!listaNumeros.contains(num)) {
                listaNumeros.add(num);
            }
        }
        return listaNumeros;

    }

    public List aniadir2productos() {
        log.info("Comprando 2 productos aleatorios...");
        try {
            List<Integer> lista = numerosAleatorios(2, listaCarrito.size());
            for (int i = 0; i < lista.size(); i++) {
                int numero = lista.get(i);
                itemList.get(numero).click();

            }

        } catch (TimeoutException timeoutException) {
            log.info("Timeout compra 2 productos: " + timeoutException.getClass().getSimpleName());
        }
        return itemList;
    }
}
