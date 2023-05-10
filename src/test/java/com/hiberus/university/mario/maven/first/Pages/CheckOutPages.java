package com.hiberus.university.mario.maven.first.Pages;

import com.hiberus.university.mario.maven.first.Utils.NumerosAleatorios;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import org.openqa.selenium.support.PageFactory;


@Slf4j
public class CheckOutPages extends AbstractPage {
    NumerosAleatorios numerosAleatorios = new NumerosAleatorios();
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement carrito;
    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkOut;
    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement nombre;
    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement apellidos;
    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement cod;
    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finish;
    @FindBy(xpath = "//input[@data-test='continue']")
    private WebElement continuar;
    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    private WebElement stringPrecios;
    @FindBy(xpath = "//div[@class='complete-text']")
    private WebElement mensaje;
    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory']")
    private List<WebElement> listaBotonesAniadir;
    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private List<WebElement> itemPrice;

    public static final String PAGE_URL = "https://www.saucedemo.com/cart.html";
    private static final String nombreUser = "a";
    private static final String apellidoUser = "a";
    private static final String codUser = "1";


    CheckOutPages(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return checkOut;
    }

    public Double verificarPrecioPedido() {
        log.info("Verificando...");
        Double precioItems = 00.0;
        try {
            for (int i = 0; i < 3; i++) {
                List<Integer> lista = numerosAleatorios.numerosAleatorios(3, listaBotonesAniadir.size());
                int numero = lista.get(i);
                listaBotonesAniadir.get(numero - 1).click();
            }

            carrito.click();

            Double PrecioTotal = 0.00;
            for (int i = 0; i < 3; i++) {
                String formato = itemPrice.get(i).getText().replace("$", "");
                Double precio = Double.parseDouble(formato);
                PrecioTotal += precio;
            }

            checkOut.click();
            nombre.sendKeys(nombreUser);
            apellidos.sendKeys(apellidoUser);
            cod.sendKeys(codUser);
            continuar.click();
            String[] soloPrecio = stringPrecios.getText().split("\\$");
            String numero = soloPrecio[1];
            precioItems = Double.parseDouble(numero);
            Assert.assertEquals(precioItems, PrecioTotal);

        } catch (TimeoutException timeoutException) {
            log.info("Timeout clicking login: " + timeoutException.getClass().getSimpleName());
        }

        return precioItems;
    }

    public String VerificarMensajePedido() {
        log.info("Verificando mensaje de pedido...");
        try {
            List<Integer> lista = numerosAleatorios.numerosAleatorios(1, 6);
            for (int i = 0; i < lista.size(); i++) {
                int numero = lista.get(i);
                listaBotonesAniadir.get(numero - 1).click();


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
        return mensaje.getText();
    }
}
