package Destinia.Pages;

import Destinia.Pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {
    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
