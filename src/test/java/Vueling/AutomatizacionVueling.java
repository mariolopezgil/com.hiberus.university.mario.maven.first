package Vueling;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class AutomatizacionVueling {

        public static void main(String[] args) throws InterruptedException {
            // Paso Inicial
            WebDriver driver;
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();

            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();

/*
* Realizar Login:
1.Entra en http://tickets.vueling.com2.Selecciona un billete de ida y vuelta de Barcelona a Madrid,
* 2 adultos, 1 niño.
* 3.La fecha de salida debe ser 4 días desde la fecha de ejecución de la prueba, la fecha de regreso es 3 días después de la salida
* 4.El usuario hace clic en "Buscar vuelos".
* 5.El usuario selecciona un vuelo al azar de la lista.
* 6.La tarifa seleccionada (Basic, Optima o Excellence) debe ser configurable.
* 7.El usuario hace clic en "Continuar".
* */

            // 1.Entra en http://tickets.vueling.com2.Selecciona un billete de ida y vuelta de Barcelona a Madrid,
            driver.get("http://tickets.vueling.com");


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
            LocalDate fechaFinal=fechaSumada.minusMonths(1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            String fechaIda = fechaFinal.format(formatter);
            String[] partesFecha1 = fechaIda.split("-");
            String anio = partesFecha1[0];
            String mes = partesFecha1[1];
            String dia = partesFecha1[2];

            // WebElement fecha1 = driver.findElement(By.xpath("//input[@id='marketDate1']"));
            // fecha1.sendKeys(fechaIda);

            LocalDate fechaSumada2 = fechaActual.plusDays(7);
            LocalDate fechaFinal2=fechaSumada2.minusMonths(1);

            String fechaVuelta = fechaFinal2.format(formatter);
            String[] partesFecha2 = fechaVuelta.split("-");
            String anio2 = partesFecha2[0];
            String mes2 = partesFecha2[1];
            String dia2 = partesFecha2[2];

            System.out.println("año"+anio+"mes"+mes+"dia"+dia);
            System.out.println("año"+anio2+"mes"+mes2+"dia"+dia2);
            //WebElement fecha2 = driver.findElement(By.xpath("//input[@id='marketDate2']"));
            //fecha2.sendKeys(fechaVuelta);

            WebElement anioMesIda = driver.findElement(By.xpath("//td[@data-handler='selectDay' and @data-month='"+mes+"' and @data-year='"+anio+"']"));
            WebElement diaIda = driver.findElement(By.xpath("//a[@class='ui-state-default']"));
            diaIda.sendKeys(dia);
            anioMesIda.click();

            WebElement anioMesVuelta = driver.findElement(By.xpath("//td[@data-handler='selectDay' and @data-month='"+mes2+"' and @data-year='"+anio2+"']"));
            WebElement diaVuelta = driver.findElement(By.xpath("//a[@class='ui-state-default']"));
            diaVuelta.sendKeys(dia2);
            anioMesVuelta.click();

            // WebElement cerrar_calendario =driver.findElement(By.xpath("//a[@id='datePickerTitleCloseButton']"));
            //cerrar_calendario.click();

            // 2 adultos, 1 niño.
            WebElement adulto = driver.findElement(By.xpath("//a[@id='DropDownListPassengerType_ADT_2']"));
            adulto.click();

            WebElement ninios = driver.findElement(By.xpath("//select[@id='AvailabilitySearchInputSearchView_DropDownListPassengerType_CHD']"));
            ninios.click();
            actions.sendKeys(ninios, Keys.ARROW_DOWN).build().perform();
            actions.sendKeys(ninios, Keys.ENTER).build().perform();

            //4.El usuario hace clic en "Buscar vuelos".
            WebElement btn_buscar = driver.findElement(By.xpath("//a[@id='AvailabilitySearchInputSearchView_btnClickToSearchNormal']"));
            btn_buscar.click();

}}
