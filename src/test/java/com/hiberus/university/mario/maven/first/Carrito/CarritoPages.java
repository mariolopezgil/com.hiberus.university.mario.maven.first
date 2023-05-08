package com.hiberus.university.mario.maven.first.Carrito;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarritoPages {
    By listaCarrito = By.xpath("//div[@class='inventory_list']");

    By itemsLista = By.xpath("//div[@class='inventory_item']");
    By carrito = By.xpath("//a[@class='shopping_cart_link']");
    By numeroCarrito = By.xpath("//span[@class='shopping_cart_badge']");
    private WebDriver driver;


    public CarritoPages(WebDriver driver) {
        this.driver = driver;
    }

    public void eliminarProductoCarrito() {
        aniadir2productos();
        List<Integer> lista = numerosAleatorios(1, 2);
        driver.findElement(carrito).click();
        for (int i = 0; i < lista.size(); i++) {
            int numero = lista.get(i);
            By productoAleatorioEliminado = By.xpath("//div[@class='cart_list'][" + numero + "]//button[@class='btn btn_secondary btn_small cart_button']");
            WebDriverWait wait = new WebDriverWait(this.driver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(productoAleatorioEliminado)).click();
        }
        Assert.assertEquals("El producto no se ha eliminado correctamente", driver.findElement(numeroCarrito).getText(), "1");


    }

    public List numerosAleatorios(int cantidad, int tamanio) {
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

    public void aniadir2productos() {
        List<Integer> lista = numerosAleatorios(2, 6);
        for (int i = 0; i < lista.size(); i++) {
            int numero = lista.get(i);
            String xpath = "//div[@class='inventory_item'][" + numero + "]//button[@class='btn btn_primary btn_small btn_inventory']";
            driver.findElement(By.xpath(xpath)).click();
        }
    }
}
