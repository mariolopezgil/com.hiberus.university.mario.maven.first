package com.hiberus.university.mario.maven.first.Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    private static PageFactory pagesFactory;
    private final LoginPage loginPage;
    private final InventarioPages inventarioPages;
    private final CarritoPages carritoPages;
    private final LogOutPages logOut;
    private final CheckOutPages checkOutPages;
    private final WebDriver driver;
    private PageFactory(WebDriver driver){
        CheckOutPages checkOutPages1;
        this.driver=driver;
        loginPage= new LoginPage(driver);
        inventarioPages = new InventarioPages(driver);
        carritoPages = new CarritoPages(driver);
        checkOutPages = new CheckOutPages(driver);
        logOut= new LogOutPages(driver);
    }
    public static void start(WebDriver driver){pagesFactory= new PageFactory(driver);}


    public WebDriver getDriver(){
        return driver;
    }
    public static PageFactory getInstance(){
        return pagesFactory;
    }
    public LoginPage getLoginPage(){
        return loginPage;
    }
    public InventarioPages getInventarioPages(){
        return inventarioPages;
    }

    public CheckOutPages getCheckOutPages() {
        return checkOutPages;
    }

    public CarritoPages getCarritoPages() {
        return carritoPages;
    }

    public LogOutPages getLogOutPages() {return logOut;
    }
}
