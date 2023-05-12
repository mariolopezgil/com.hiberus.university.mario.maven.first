package com.hiberus.university.mario.maven.first.StepDefs;

import com.hiberus.university.mario.maven.first.Pages.LogOutPages;
import com.hiberus.university.mario.maven.first.Pages.LoginPage;
import com.hiberus.university.mario.maven.first.Pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LogOutPageSteps {
    PageFactory pageFactory= PageFactory.getInstance();
    LogOutPages logOutPages= pageFactory.getLogOutPages();
    LoginPage loginPage = pageFactory.getLoginPage();
    @Given("the user is logged successfully and is into the inventory")
    public void theUserIsLoggedSuccessfullyAndIsIntoTheInventory() {
        loginPage.login("standard_user","secret_sauce");

    }
    @When("the user clicks on the dropdown menu")
    public void theUserClicksOnTheDropdownMenu() {
        logOutPages.clickMenu();
    }

    @And("the user selects and clicks on the logOut option")
    public void theUserSelectsAndClicksOnTheLogOutOption() {
        logOutPages.clickLogOut();
    }

    @Then("the user should be taken to the login page")
    public void theUserShouldBeTakenToTheLoginPage() {
        Assert.assertEquals(LoginPage.PAGE_URL,PageFactory.getInstance().getDriver().getCurrentUrl());
    }
}
