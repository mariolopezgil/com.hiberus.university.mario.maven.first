package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class InvetarioPages {

    By listaCarrito = By.xpath("//div[@class='inventory_list']");

    By itemsLista = By.xpath("//div[@class='inventory_item']");

    By camiseta = By.xpath("//div[@class='inventory_item_name' and contains(text(), 'Sauce Labs Bolt T-Shirt')]");

    By btn_aniadir = By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']");

    By numeroCarrito = By.xpath("//span[@class='shopping_cart_badge']");
    By add_btn = By.xpath("//button[@data-test='add-to-cart-sauce-labs-onesie']");

    By remove_btn = By.xpath("//button[@data-test='remove-sauce-labs-onesie']");
    By nombresElemento= By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_name']");
    By az= By.xpath("//select[@data-test='product_sort_container']//option[@value='az']");
    By lohi= By.xpath("//select[@data-test='product_sort_container']//option[@value='lohi']");
    By hilo= By.xpath("//select[@data-test='product_sort_container']//option[@value='hilo']");
    By precioElemento =By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_price']");

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

    public void clickAniadirSudaderayEliminarla(){
        driver.findElement(add_btn).click();
        WebDriverWait wait = new WebDriverWait(this.driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(remove_btn)).click();

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
    public void aniadir3productos(){
        List<Integer> lista = numerosAleatorios(3, 6);
        for (int i = 0; i < lista.size(); i++) {
            int numero = lista.get(i);
            String xpath = "//div[@class='inventory_item'][" + numero + "]//button[@class='btn btn_primary btn_small btn_inventory']";
            driver.findElement(By.xpath(xpath)).click();
        }
    }
    public void ordenAlfabetico(){
        List <WebElement> elementos = driver.findElements(nombresElemento);
        List<String> nombres = new ArrayList<>();
        for (WebElement elemento : elementos) {
            nombres.add(elemento.getText());
        }
        Collections.sort(nombres);

        driver.findElement(az).click();

        List <WebElement> nombresEsperados = driver.findElements(nombresElemento);
        List<String> nombresXpected = new ArrayList<>();
        for (WebElement elemento : nombresEsperados) {
            nombresXpected.add(elemento.getText());
        }

        Assert.assertEquals("El resultado de las listas ordenadas alfabeticamente no son iguales", nombresXpected, nombres);
    }
    public void mayorMenor(){
        List <WebElement> elementos = driver.findElements(precioElemento);
        List<Double> precios = new ArrayList<>();
        for (WebElement elemento : elementos) {
            String nombre = elemento.getText();
            String precioElementos = nombre.replace("$", "");
            Double precioDouble = Double.parseDouble(precioElementos);
            precios.add(precioDouble);
        }
        Collections.sort(precios,Collections.reverseOrder());

        driver.findElement(hilo).click();

        List <WebElement> preciosEsperados = driver.findElements(precioElemento);
        List<Double> preciosXpected = new ArrayList<>();
        for (WebElement elemento : preciosEsperados) {
            String nombre = elemento.getText();
            String precioElementos2 = nombre.replace("$", "");
            Double precioDouble = Double.parseDouble(precioElementos2);
            preciosXpected.add(precioDouble);
        }

        Assert.assertEquals("El resultado de las listas ordenadas alfabeticamente no son iguales", preciosXpected, precios);
    }
    public void menorMayor(){
        List <WebElement> elementos = driver.findElements(precioElemento);
        List<Double> precios = new ArrayList<>();
        for (WebElement elemento : elementos) {
            String nombre = elemento.getText();
            String precioElementos = nombre.replace("$", "");
            Double precioDouble = Double.parseDouble(precioElementos);
            precios.add(precioDouble);
        }
        Collections.sort(precios);

        driver.findElement(lohi).click();

        List <WebElement> preciosEsperados = driver.findElements(precioElemento);
        List<Double> preciosXpected = new ArrayList<>();
        for (WebElement elemento : preciosEsperados) {
            String nombre = elemento.getText();
            String precioElementos2 = nombre.replace("$", "");
            Double precioDouble = Double.parseDouble(precioElementos2);
            preciosXpected.add(precioDouble);
        }

        Assert.assertEquals("El resultado de las listas ordenadas alfabeticamente no son iguales", preciosXpected, precios);
    }
}
