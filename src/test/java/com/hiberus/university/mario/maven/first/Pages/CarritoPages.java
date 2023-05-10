package com.hiberus.university.mario.maven.first.Pages;

import com.hiberus.university.mario.maven.first.Utils.NumerosAleatorios;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.support.PageFactory;


@Slf4j
public class CarritoPages extends AbstractPage {
    NumerosAleatorios numerosAleatorios = new NumerosAleatorios();
    @FindBy(xpath = "//div[@class='inventory_list']")
    private List<WebElement> listaCarrito;
    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<WebElement> itemList;
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement carrito;
    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    private WebElement numeroCarrito;
    @FindBy(xpath = "//button[@class='btn btn_secondary btn_small cart_button']")
    private List<WebElement> lisatRemove;
    @FindBy(xpath = "//div[@class='inventory_item']//button[@class='btn btn_primary btn_small btn_inventory']")
    private List<WebElement> addItem;

    public static final String PAGE_URL = "https://www.saucedemo.com/";

    CarritoPages(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public String eliminarProductoCarrito() {
        log.info("Eliminando un producto random...");
        try {
            List<Integer> lista = numerosAleatorios.numerosAleatorios(1, 2);
            carrito.click();
            for (int i = 0; i < lista.size(); i++) {
                int numero = lista.get(i);
                lisatRemove.get(numero-1).click();

            }

        } catch (TimeoutException timeoutException) {
            log.info("Timeout eliminar 1 producto aleatorio: " + timeoutException.getClass().getSimpleName());
        }
        return numeroCarrito.getText();
    }


    public void aniadir2productos() {
        log.info("Comprando 2 productos aleatorios...");
        try {
            List<Integer> lista = numerosAleatorios.numerosAleatorios(2, 6);
            for (int i = 0; i < lista.size(); i++) {
                int numero = lista.get(i);
                addItem.get(numero-1).click();

            }

        } catch (TimeoutException timeoutException) {
            log.info("Timeout compra 2 productos: " + timeoutException.getClass().getSimpleName());
        }

    }
}
