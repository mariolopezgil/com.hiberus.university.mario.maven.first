package Vueling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CA_5 {
    String url = "http://tickets.vueling.com";
    WebDriver driver;

    @Before
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(url);



    }

    @Test
    public void adulto5niños2bebes() {

        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement cookies = driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
        cookies.click();

        WebElement origen = driver.findElement(By.xpath("//input[@id='AvailabilitySearchInputSearchView_TextBoxMarketOrigin1']"));
        origen.sendKeys("Barcelona");
        Actions actions = new Actions(driver);
        actions.sendKeys(origen, Keys.ENTER).build().perform();


        WebElement destino = driver.findElement(By.xpath("//input[@id='AvailabilitySearchInputSearchView_TextBoxMarketDestination1']"));
        destino.sendKeys("Madrid");
        actions.sendKeys(destino, Keys.ENTER).build().perform();


        //3.La fecha de salida debe ser 4 días desde la fecha de ejecución de la prueba, la fecha de regreso es 3 días después de la salida

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaSumada = fechaActual.plusDays(3);
        LocalDate fechaFinal = fechaSumada.minusMonths(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        String fechaIda = fechaFinal.format(formatter);
        String[] partesFecha1 = fechaIda.split("-");
        String anio = partesFecha1[0];
        String mes = partesFecha1[1];
        String dia = partesFecha1[2];


        LocalDate fechaSumada2 = fechaActual.plusDays(7);
        LocalDate fechaFinal2 = fechaSumada2.minusMonths(1);

        String fechaVuelta = fechaFinal2.format(formatter);
        String[] partesFecha2 = fechaVuelta.split("-");
        String anio2 = partesFecha2[0];
        String mes2 = partesFecha2[1];
        String dia2 = partesFecha2[2];

        System.out.println("año" + anio + "mes" + mes + "dia" + dia);
        System.out.println("año" + anio2 + "mes" + mes2 + "dia" + dia2);



        WebElement anioMesIda = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@data-handler='selectDay' and @data-month='" + mes + "' and @data-year='" + anio + "']//a[text()='" + dia + "']")));
        anioMesIda.click();


        WebElement anioMesVuelta = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@data-handler='selectDay' and @data-month='" + mes2 + "' and @data-year='" + anio2 + "']//a[text()='" + dia2 + "']")));
        anioMesVuelta.click();


        // 1 Adultos.
        WebElement adulto = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='wrapper_elForm_section marginLeft0']//a[@id='DropDownListPassengerType_ADT_1']")));
        adulto.click();

        //5 niños
        Select selectninios = new Select(driver.findElement(By.xpath("//select[@id='AvailabilitySearchInputSearchView_DropDownListPassengerType_CHD']")));
        selectninios.selectByValue("5");

        //2 Bebes
        WebElement esperado = driver.findElement(By.xpath("//select[@id='AvailabilitySearchInputSearchView_DropDownListPassengerType_INFANT']//option[@value='2' and @disabled='disabled']"));

        Assert.assertTrue("El elemento no esta desactivado",esperado.isDisplayed());

        //4.El usuario hace clic en "Buscar vuelos".
       // WebElement btn_buscar = driver.findElement(By.xpath("//a[@id='AvailabilitySearchInputSearchView_btnClickToSearchNormal']"));
        //btn_buscar.click();

    }

    @After
    public void TearDown() {
        driver.close();
    }
}
