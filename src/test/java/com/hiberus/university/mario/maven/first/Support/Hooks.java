package com.hiberus.university.mario.maven.first.Support;

import com.hiberus.university.mario.maven.first.Pages.PageFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
public class Hooks {
    public static WebDriver driver;
    @Before
    public void before(Scenario scenario){
        log.info("starting"+scenario.getName());
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        PageFactory.start(driver);
    }
    @After
    public void after(Scenario scenario){
        log.info("ending"+scenario.getName());
        driver.close();
    }
}
