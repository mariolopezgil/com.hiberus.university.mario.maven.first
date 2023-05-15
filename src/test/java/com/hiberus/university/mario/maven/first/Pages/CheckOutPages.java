package com.hiberus.university.mario.maven.first.Pages;


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.support.PageFactory;


@Slf4j
public class CheckOutPages extends AbstractPage {
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

    public List verificarPrecioPedido() {
        Double precioItems = 00.0;
        ArrayList <Double> precios = new ArrayList<>();
            Double PrecioTotal = 0.00;
            for (int i = 0; i < 3; i++) {
                String formato = itemPrice.get(i).getText().replace("$", "");
                Double precio = Double.parseDouble(formato);
                PrecioTotal += precio;
            }
            precios.add(PrecioTotal);
            String[] soloPrecio = stringPrecios.getText().split("\\$");
            String numero = soloPrecio[1];
            precioItems = Double.parseDouble(numero);
          precios.add(precioItems);

        return precios;
    }

    public String VerificarMensajePedido() {
        return mensaje.getText();
    }
    public void clickCarrito(){
        carrito.click();
    }
    public void clickCheckout(){
        checkOut.click();
    }
    public void fillForm(String name,String lastname, String codUser){
        nombre.sendKeys(nombreUser);
        apellidos.sendKeys(apellidoUser);
        cod.sendKeys(codUser);
    }
    public void clickContinue(){
        continuar.click();
    }
    public void clickFinish(){
        finish.click();
    }
}
