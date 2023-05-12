package com.hiberus.university.mario.maven.first.StepDefs;

import com.hiberus.university.mario.maven.first.Pages.CheckOutPages;
import com.hiberus.university.mario.maven.first.Pages.LoginPage;
import com.hiberus.university.mario.maven.first.Pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CheckOutPageSteps {
    PageFactory pageFactory= PageFactory.getInstance();
    CheckOutPages checkOutPages= pageFactory.getCheckOutPages();
    LoginPage loginPage = pageFactory.getLoginPage();
    @And("the user accesses the cart \\(clicks the cart button)")
    public void theUserAccessesTheCartClicksTheCartButton() {
      checkOutPages.clickCarrito();
    }

    @And("the user clicks the checkout button")
    public void theUserClicksTheCheckoutButton() {
      checkOutPages.clickCheckout();
    }

    @And("the user fills out the form \\(inputs {string}, {string}, {string})")
    public void theUserFillsOutTheFormInputs(String name, String lastname, String cod) {
        checkOutPages.fillForm(name,lastname,cod);
    }

    @And("the user clicks the continue button")
    public void theUserClicksTheContinueButton() {
       checkOutPages.clickContinue();
    }
    @And("the user clicks the finish button")
    public void theUserClicksTheFinishButton() {
      checkOutPages.clickFinish();
    }

    @Then("the user should be able to see the confirmation message that the order has been placed")
    public void theUserShouldBeAbleToSeeTheConfirmationMessageThatTheOrderHasBeenPlaced() {
        Assert.assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!",checkOutPages.VerificarMensajePedido());
    }

    @Then("the user should be able to verify that the sum of the items they have added is equal to the amount shown on the checkout page under items price")
    public void theUserShouldBeAbleToVerifyThatTheSumOfTheItemsTheyHaveAddedIsEqualToTheAmountShownOnTheCheckoutPageUnderItemsPrice() {
        Assert.assertEquals(checkOutPages.verificarPrecioPedido().get(0),checkOutPages.verificarPrecioPedido().get(1));
    }
    @Given("the user is logged successfully and is into the inventory")
    public void theUserIsLoggedSuccessfullyAndIsIntoTheInventory() {
        loginPage.login("standard_user","secret_sauce");

    }
}
