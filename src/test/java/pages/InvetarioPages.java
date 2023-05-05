package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InvetarioPages {
    By listaCarrito = By.xpath("//div[@class='inventory_list']");

    By itemsLista = By.xpath("//div[@class='inventory_item']");

    By camiseta = By.xpath("//div[@class='inventory_item_name' and contains(text(), 'Sauce Labs Bolt T-Shirt')]");

    By btn_aniadir = By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']");

    By numeroCarrito = By.xpath("//span[@class='shopping_cart_badge']");
    private WebDriver driver;
    private WebElement listaInventario;
    private List<WebElement> elementos;

    public InvetarioPages(WebDriver driver) {
        this.driver = driver;
    }

    public int obtenerNumeroElementos(WebDriver driver) {
        this.driver = driver;
        listaInventario = driver.findElement(listaCarrito);
        elementos = listaInventario.findElements(itemsLista);
        return elementos.size();
    }
    public WebElement getCamiseta(){

        return    driver.findElement(camiseta);

    }
    public void clickAniadir(){

        driver.findElement(btn_aniadir).click();

    }

    public String obtenerNumeroCarrito(){
        return driver.findElement(numeroCarrito).getText();
    }
}
