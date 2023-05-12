package com.hiberus.university.mario.maven.first.Pages;

import com.hiberus.university.mario.maven.first.Utils.NumerosAleatorios;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.PageFactory;


@Slf4j
public class InventarioPages extends AbstractPage {
    NumerosAleatorios numerosAleatorios = new NumerosAleatorios();
    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";
    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<WebElement> itemList;
    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    private WebElement numeroCarrito;
    @FindBy(xpath = "//div[@class='inventory_item']//div[@class='inventory_item_name']")
    private List<WebElement> itemName;
    @FindBy(xpath = "//select[@data-test='product_sort_container']//option[@value='az']")
    private WebElement az;
    @FindBy(xpath = "//select[@data-test='product_sort_container']//option[@value='hilo']")
    private WebElement hilo;
    @FindBy(xpath = "//select[@data-test='product_sort_container']//option[@value='lohi']")
    private WebElement lohi;
    @FindBy(xpath = "//div[@class='inventory_item']//div[@class='inventory_item_price']")
    private List<WebElement> itemPrice;
    @FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-onesie']")
    private WebElement addSauceLabs;
    @FindBy(xpath = "//button[@data-test='remove-sauce-labs-onesie']")
    private WebElement removeSauceLabs;
    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory']")
    private List<WebElement> listaBotonesAniadir;


    InventarioPages(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return removeSauceLabs;
    }

    public int obtenerNumeroElementos() {

        int cantidadElementos = itemList.size();

        return cantidadElementos;
    }

    public boolean getCamiseta(String nombre) {
        WebElement producto = (WebElement) By.xpath("//div[@class='inventory_item_name' and contains(text(), '"+nombre+"')]");

        return producto.isDisplayed();

    }

    public void clickAniadir(String nombre) {
        WebElement aniadirProducto = (WebElement) By.xpath("//div[@class='inventory_item_name' and contains(text(), '"+nombre+"')]//button[@class='btn btn_primary btn_small btn_inventory']");
        aniadirProducto.click();
    }
    public void clickRemove(String nombre) {
        WebElement aniadirProducto = (WebElement) By.xpath("//div[@class='inventory_item_name' and contains(text(), '"+nombre+"')]//button[@class='btn btn_secondary btn_small btn_inventory']");
        aniadirProducto.click();
    }

    public String obtenerNumeroCarrito() {

        return numeroCarrito.getText();

    }
    public String aniadir3productos(int numeroProductos) {

        log.info("...");
        try {
            for (int i = 0; i < 3; i++) {
                List<Integer> lista = numerosAleatorios.numerosAleatorios(numeroProductos, listaBotonesAniadir.size());
                int numero = lista.get(i);
                listaBotonesAniadir.get(numero - 1).click();
            }

        } catch (TimeoutException timeoutException) {
            log.info("Timeout: " + timeoutException.getClass().getSimpleName());
        }
        return numeroCarrito.getText();
    }
    public boolean addSauceLabsEnabled(String nombre) {
        WebElement aniadirProducto = (WebElement) By.xpath("//div[@class='inventory_item_name' and contains(text(), '"+nombre+"')]//button[@class='btn btn_primary btn_small btn_inventory']");
        return aniadirProducto.isEnabled();

    }
    public List sortInvenory(String sort){
        ArrayList listas = new ArrayList();
        List<String> nombres = new ArrayList<>();
        List<Double> precios = new ArrayList<>();
        if (sort.equals("az")){
            for (WebElement elemento : itemName) {
                nombres.add(elemento.getText());
            }
            Collections.sort(nombres);


        }else if(sort.equals("lohi")){
            for (WebElement elemento : itemPrice) {
                String nombre = elemento.getText();
                String precioElementos = nombre.replace("$", "");
                Double precioDouble = Double.parseDouble(precioElementos);
                precios.add(precioDouble);
            }
            Collections.sort(precios);

        }else if(sort.equals("hilo")){
            for (WebElement elemento : itemPrice) {
                String nombre = elemento.getText();
                String precioElementos = nombre.replace("$", "");
                Double precioDouble = Double.parseDouble(precioElementos);
                precios.add(precioDouble);
            }
            Collections.sort(precios, Collections.reverseOrder());

        }
          listas.add(nombres);
          listas.add(precios);
        return listas ;
    }
    public List sortInventoryExpected(String sort){
        ArrayList listas = new ArrayList();
        List<Double> preciosXpected = new ArrayList<>();
        List<String> nombresXpected = new ArrayList<>();
        if (sort.equals("az")){
            for (WebElement elemento : itemName) {
                nombresXpected.add(elemento.getText());
            }

        }else if(sort.equals("lohi")){
            for (WebElement elemento : itemPrice) {
                String nombre = elemento.getText();
                String precioElementos2 = nombre.replace("$", "");
                Double precioDouble = Double.parseDouble(precioElementos2);
                preciosXpected.add(precioDouble);
            }

        }else if(sort.equals("hilo")){
            for (WebElement elemento : itemPrice) {
                String nombre = elemento.getText();
                String precioElementos2 = nombre.replace("$", "");
                Double precioDouble = Double.parseDouble(precioElementos2);
                preciosXpected.add(precioDouble);
            }

        }
        listas.add(nombresXpected);
        listas.add(preciosXpected);
        return listas;
    }
    public void sortMenu(String sort){
     if (sort.equals("az")){az.click();

    }else if(sort.equals("lohi")){lohi.click();

    }else if(sort.equals("hilo")){hilo.click();}}}

