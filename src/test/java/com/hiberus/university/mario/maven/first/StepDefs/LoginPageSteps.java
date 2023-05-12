package com.hiberus.university.mario.maven.first.StepDefs;

import com.hiberus.university.mario.maven.first.Pages.InventarioPages;
import com.hiberus.university.mario.maven.first.Pages.LoginPage;
import com.hiberus.university.mario.maven.first.Pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;


@Slf4j
public class LoginPageSteps {
    PageFactory pageFactory= PageFactory.getInstance();
    LoginPage loginPage = pageFactory.getLoginPage();

    @Given("the user is on home page")
    public void theUserIsOnHomePage() {
        log.info("the user is on home page");
        loginPage.navigateTo(LoginPage.PAGE_URL);
    }

    @And("the user provide the username {string} and password {string}")
    public void theUserProvideTheUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user click the login button")
    public void theUserClickTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user is logged successfully and is into the inventory page")
    public void theUserIsLoggedSuccessfullyAndIsIntoTheInventoryPage() {
        Assert.assertEquals(InventarioPages.PAGE_URL,PageFactory.getInstance().getDriver().getCurrentUrl());
    }

    @Then("the user should be shown an invalid message")
    public void theUserShouldBeShownAnInvalidMessage() {

        Assert.assertTrue(loginPage.hasUsernamePasswordError());
    }
}
