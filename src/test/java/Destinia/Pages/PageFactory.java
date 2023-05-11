package Destinia.Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {

    private static  PageFactory pagesFactory;

    private final WebDriver driver;

    private PageFactory(WebDriver driver) {
        this.driver = driver;


    }

    public static void start(WebDriver driver) {
        pagesFactory = new PageFactory(driver);
    }


    public WebDriver getDriver() {
        return driver;
    }

    public static PageFactory getInstance() {
        return pagesFactory;
    }



}
