package com.hiberus.university.mario.maven.first.LogOut;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LogOutPages {
    By menu = By.xpath("//button[@id='react-burger-menu-btn']");
    By logOut = By.xpath("//a[@id='logout_sidebar_link']");
    private WebDriver driver;


    public LogOutPages(WebDriver driver) {
        this.driver = driver;
    }

    public void comprobarLogOut() {
        driver.findElement(menu).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logOut)).click();
        String url = "https://www.saucedemo.com/";
        Assert.assertEquals("No has sido exitoso cerrar sesion", url, driver.getCurrentUrl());
    }

}
