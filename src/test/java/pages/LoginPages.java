package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPages {
    WebDriver driver;

    By usuario = By.xpath("//input[@data-test='username']");

    By password = By.xpath("//input[@data-test='password']");

    By errorMensaje =By.xpath("//div[@class='error-message-container error']");

    By btn_login = By.xpath("//input[@data-test='login-button']");

    public LoginPages(WebDriver driver){

        this.driver = driver;

    }
    public void setUserName(String strUserName){

        driver.findElement(usuario).sendKeys(strUserName);

    }

    //Set password in password textbox

    public void setPassword(String strPassword){

        driver.findElement(password).sendKeys(strPassword);

    }

    //Click on login button

    public void clickLogin(){

        driver.findElement(btn_login).click();

    }

    //Get the title of Login Page

    public WebElement getLoginError(){

        return    driver.findElement(errorMensaje);

    }

    /**

     * This POM method will be exposed in test case to login in the application

     * @param strUserName

     * @param strPasword

     * @return

     */

    public void loginToGuru99(String strUserName,String strPasword){

        //Fill user name

        this.setUserName(strUserName);

        //Fill password

        this.setPassword(strPasword);

        //Click Login button

        this.clickLogin();
    }

}
