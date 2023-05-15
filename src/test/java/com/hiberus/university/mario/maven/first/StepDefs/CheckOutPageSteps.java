package com.hiberus.university.mario.maven.first.StepDefs;

import com.hiberus.university.mario.maven.first.Pages.CheckOutPages;
import com.hiberus.university.mario.maven.first.Pages.InventarioPages;
import com.hiberus.university.mario.maven.first.Pages.LoginPage;
import com.hiberus.university.mario.maven.first.Pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CheckOutPageSteps {
    PageFactory pageFactory= PageFactory.getInstance();
    CheckOutPages checkOutPages= pageFactory.getCheckOutPages();
    InventarioPages inventarioPages= pageFactory.getInventarioPages();
    @And("the user accesses the cart \\(clicks the cart button)")
    public void theUserAccessesTheCartClicksTheCartButton() {
      checkOutPages.clickCarrito();
    }

    @And("the user clicks on the checkout button")
    public void theUserClicksOnTheCheckoutButton() {checkOutPages.clickCheckout();}

    @And("the user fills out the form with name {string}, lastname {string}, and COD {string}")
    public void theUserFillsOutTheFormWithNameLastnameAndCOD(String name, String lastname, String cod) {
        checkOutPages.fillForm(name, lastname, cod);
    }

    @And("the user clicks on the continue button")
    public void theUserClicksOnTheContinueButton() {checkOutPages.clickContinue();}
    @Then("the user should verify that the total price of items in the checkout matches the sum of individual prices in the inventory")
    public void theUserShouldVerifyThatTheTotalPriceOfItemsInTheCheckoutMatchesTheSumOfIndividualPricesInTheInventory() {
        Assert.assertEquals(checkOutPages.verificarPrecioPedido().get(1),checkOutPages.verificarPrecioPedido().get(0));
    }

    @When("the user adds {int} product to the cart")
    public void theUserAddsProductToTheCart(int numero) {inventarioPages.aniadir3productos(numero);
    }

    @And("the user clicks on the finish button")
    public void theUserClicksOnTheFinishButton() {checkOutPages.clickFinish();}

    @Then("the user should see a confirmation message that the order has been placed")
    public void theUserShouldSeeAConfirmationMessageThatTheOrderHasBeenPlaced() {
        Assert.assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!",checkOutPages.VerificarMensajePedido());
    }
}
