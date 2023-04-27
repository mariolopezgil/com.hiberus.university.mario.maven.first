import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class LoginIncorrecto {
    public static void main(String[] args) throws InterruptedException {
        // Paso Inicial
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();

/*
* Realizar Login:
1. Ir a la página https://www.saucedemo.com/
2. Escribir el username standard_user
3. Escribir el password secret_sauce
4. Pulsar en el botón del Login.
5. Validar que hemos accedido correctamente a la página, comprobando que se muestra la URL https://www.saucedemo.com/inventory.html
* */

// Step 1
        driver.get("https://www.saucedemo.com/");

// Step 2

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_use");

// Step 3
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

// Step 4
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

// Step 5


        try {
            WebElement mensajeError = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
            if (mensajeError.isDisplayed()) {
                System.out.println("sale mensaje de error");
            } else {
                System.out.println("no sale mensaje de error");
            }
        } catch (NoSuchElementException e) {
            // Manejo de excepción si no se encuentra el elemento en la página
            System.out.println("El elemento no se encontró en la página.");
        }

        Thread.sleep(50000);
        driver.close();
    }
}
