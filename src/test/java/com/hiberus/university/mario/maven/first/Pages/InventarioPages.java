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
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
public class InventarioPages extends AbstractPage {
    public static final String PAGE_URL = "https://www.saucedemo.com/";
    @FindBy(xpath ="//div[@class='inventory_list']" )
    private List<WebElement> listaInvenatrio;
    @FindBy(xpath ="//div[@class='inventory_item']" )
    private List<WebElement> itemList;
    @FindBy(xpath ="//div[@class='inventory_item_name' and contains(text(), 'Sauce Labs Bolt T-Shirt')]" )
    private WebElement camiseta;
    @FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']")
    private WebElement add_button;
    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    private WebElement numeroCarrito;
    @FindBy(xpath ="//div[@class='inventory_item']//div[@class='inventory_item_name']" )
    private List<WebElement> itemName;
    @FindBy(xpath = "//select[@data-test='product_sort_container']//option[@value='az']")
    private WebElement az;
    @FindBy(xpath = "//select[@data-test='product_sort_container']//option[@value='hilo']")
    private WebElement hilo;
    @FindBy(xpath = "//select[@data-test='product_sort_container']//option[@value='lohi']")
    private WebElement lohi;
    @FindBy(xpath = "//div[@class='inventory_item']//div[@class='inventory_item_price']")
    private List<WebElement> itemPrice;
    @FindBy(xpath ="//button[@data-test='add-to-cart-sauce-labs-onesie']" )
    private WebElement addSauceLabs;
    @FindBy(xpath = "//button[@data-test='remove-sauce-labs-onesie']")
    private WebElement removeSauceLabs;
    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory']")
    private List <WebElement> listaBotonesAniadir;



    InventarioPages(WebDriver driver) {
        super(driver);
    }

    public void obtenerNumeroElementos() {
        log.info("Obteniendo número de elementos...");
        int cantidadElementos = itemList.size();
        log.info("Número de elementos: " + cantidadElementos);

    }
    public boolean getCamiseta(){

            return    camiseta.isDisplayed();

    }
    public void clickAniadir(){

        log.info("...");
        try {
            add_button.click();
        } catch (TimeoutException timeoutException) {
            log.info("Timeout: " + timeoutException.getClass().getSimpleName());
        }

    }

    public String obtenerNumeroCarrito(){

        return numeroCarrito.getText();

    }

    public void clickAniadirSudaderayEliminarla(){
        log.info("...");
        try {
            addSauceLabs.click();
            removeSauceLabs.click();
        } catch (TimeoutException timeoutException) {
            log.info("Timeout: " + timeoutException.getClass().getSimpleName());
        }

    }
    public List numerosAleatorios(int cantidad,int tamanio){
        List<Integer> listaNumeros = new ArrayList<>();
        Random random = new Random();

        while (listaNumeros.size() < cantidad) {
            int num = random.nextInt(itemList.size()) + 1;
            if (!listaNumeros.contains(num)) {
                listaNumeros.add(num);
            }
        }
        return listaNumeros;

    }
    public void aniadir3productos(){

        log.info("...");
        try {
            List<Integer> lista = numerosAleatorios(3, itemList.size());
            for (int i = 0; i < lista.size(); i++) {
                int numero = lista.get(i);
               listaBotonesAniadir.get(numero).click();
            }

        } catch (TimeoutException timeoutException) {
            log.info("Timeout: " + timeoutException.getClass().getSimpleName());
        }
    }
    public void ordenAlfabetico(){
        log.info("...");
        try {
            List<String> nombres = new ArrayList<>();
            for (WebElement elemento : itemName) {
                nombres.add(elemento.getText());
            }
            Collections.sort(nombres);

           az.click();


            List<String> nombresXpected = new ArrayList<>();
            for (WebElement elemento : itemName) {
                nombresXpected.add(elemento.getText());
            }
        } catch (TimeoutException timeoutException) {
            log.info("Timeout: " + timeoutException.getClass().getSimpleName());
        }
    }
    public void mayorMenor(){
        log.info("...");
        try {

            List<Double> precios = new ArrayList<>();
            for (WebElement elemento : itemPrice) {
                String nombre = elemento.getText();
                String precioElementos = nombre.replace("$", "");
                Double precioDouble = Double.parseDouble(precioElementos);
                precios.add(precioDouble);
            }
            Collections.sort(precios,Collections.reverseOrder());

            hilo.click();


            List<Double> preciosXpected = new ArrayList<>();
            for (WebElement elemento : itemPrice) {
                String nombre = elemento.getText();
                String precioElementos2 = nombre.replace("$", "");
                Double precioDouble = Double.parseDouble(precioElementos2);
                preciosXpected.add(precioDouble);
            }

        } catch (TimeoutException timeoutException) {
            log.info("Timeout: " + timeoutException.getClass().getSimpleName());
        }
    }
    public void menorMayor(){
        log.info("...");
        try {

            List<Double> precios = new ArrayList<>();
            for (WebElement elemento : itemPrice) {
                String nombre = elemento.getText();
                String precioElementos = nombre.replace("$", "");
                Double precioDouble = Double.parseDouble(precioElementos);
                precios.add(precioDouble);
            }
            Collections.sort(precios);

            lohi.click();


            List<Double> preciosXpected = new ArrayList<>();
            for (WebElement elemento : itemPrice) {
                String nombre = elemento.getText();
                String precioElementos2 = nombre.replace("$", "");
                Double precioDouble = Double.parseDouble(precioElementos2);
                preciosXpected.add(precioDouble);

        }
        } catch (TimeoutException timeoutException) {
            log.info("Timeout: " + timeoutException.getClass().getSimpleName());
        }
    }

}
