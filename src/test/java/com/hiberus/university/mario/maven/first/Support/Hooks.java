package com.hiberus.university.mario.maven.first.Support;

import com.hiberus.university.mario.maven.first.Pages.PageFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Date;


@Slf4j
public class Hooks {
    public static WebDriver driver;
    @Before
    public void before(Scenario scenario){
        log.info("starting"+scenario.getName());
        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        if ("chrome".equals(browser)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            if (headless) {
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);
        } else if ("firefox".equals(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
                options.setHeadless(true);
            }
            driver = new FirefoxDriver(options);

        } else if ("edge".equals(browser)) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(options);

        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }
       driver.manage().window().maximize();
       driver.manage().deleteAllCookies();
       PageFactory.start(driver);
    }
    @After
    public void after(Scenario scenario) {
        log.info("ending " + scenario.getName());
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot)
                    driver).getScreenshotAs(OutputType.BYTES);
            long time = new Date().getTime();
            String outputName = "screenshot_" + time + ".png";
            scenario.attach(screenshot, "image/png", outputName);
        }
        driver.close();
    }
}
