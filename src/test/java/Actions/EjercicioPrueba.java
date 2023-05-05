package Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPages;

public class EjercicioPrueba {
    String url = "https://the-internet.herokuapp.com/hovers";
    WebDriver driver;


    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(url);

    }

    @Test
    public void validationCorrecto() {
        Actions actions= new Actions(driver);
        WebElement dragme = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement dropme = driver.findElement(By.xpath("//div[@id='droppable']"));
        WebElement dropped = driver.findElement(By.xpath("//div[@id='droppable']//p"));
        actions.dragAndDrop(dragme,dropme).build().perform();
        Assert.assertEquals("El login es incorrecto","Dropped!",dropped.getText());
    }
    @Test
    public void a() throws InterruptedException {
        Actions actions= new Actions(driver);
        WebElement view = driver.findElement(By.xpath("//a[@href='/users/3' and contains(text(), 'View profile')]"));
        WebElement imagen = driver.findElement(By.xpath("//a[@href='/users/3' and contains(text(), 'View profile')]"));
        actions.moveToElement(imagen);
        actions.moveToElement(view).click().build();
        Thread.sleep(5000);
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals("No es la pagina correcta","https://the-internet.herokuapp.com/users/3",actualUrl);
    }


    @After
    public void tearDown() {
        driver.close();
    }
}


