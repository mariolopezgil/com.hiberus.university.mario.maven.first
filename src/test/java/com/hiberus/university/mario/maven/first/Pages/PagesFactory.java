package com.hiberus.university.mario.maven.first.Pages;

import org.openqa.selenium.WebDriver;

public class PagesFactory {
    private static PagesFactory pagesFactory;
    private static LoginPage loginPage;
    private static InventarioPages inventarioPages;
    private static CarritoPages carritoPages;
    private static LogOut logOut;
    private static CheckOutPages checkOutPages;
    private final WebDriver driver;
    private PagesFactory(WebDriver driver){
        this.driver=driver;
        //inicializar nuestros Pages

    }
    public static void start(WebDriver driver){
        pagesFactory= new PagesFactory(driver);
        loginPage= new LoginPage(driver);
        inventarioPages = new InventarioPages(driver);
        carritoPages = new CarritoPages(driver);
        checkOutPages= new CheckOutPages(driver);
        logOut= new LogOut(driver);
        checkOutPages=new CheckOutPages(driver);

    }
    public WebDriver getDriver(){
        return driver;
    }
}
