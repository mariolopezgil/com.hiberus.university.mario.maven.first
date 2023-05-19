package com.hiberus.university.mario.maven.first.StepDefs;

import com.hiberus.university.mario.maven.first.Pages.LogOutPages;
import com.hiberus.university.mario.maven.first.Pages.LoginPage;
import com.hiberus.university.mario.maven.first.Pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LogOutPageSteps {
    PageFactory pageFactory= PageFactory.getInstance();
    LogOutPages logOutPages= pageFactory.getLogOutPages();
    @When("the user clicks on the account dropdown menu")
    public void theUserClicksOnTheAccountDropdownMenu() {
        logOutPages.clickMenu();
    }

    @And("the user selects the log out option")
    public void theUserSelectsTheLogOutOption() throws InterruptedException {
        logOutPages.clickLogOut();
    }

    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLoginPage() {
        Assert.assertEquals(LoginPage.PAGE_URL,PageFactory.getInstance().getDriver().getCurrentUrl());
    }
}
