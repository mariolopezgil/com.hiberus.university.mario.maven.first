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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOut {
    String url="https://www.saucedemo.com/";
    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options= new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(url);

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");


        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");


        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();
    }
    @Test
    public void comprobarLogOut(){
        WebElement menu = driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']"));
        menu.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='logout_sidebar_link']")));
        WebElement logout = driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
        logout.click();
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("https://www.saucedemo.com/", actualUrl);
    }
    @After
    public void tearDown(){
        driver.close();
    }
}
