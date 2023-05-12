package com.hiberus.university.mario.maven.first.StepDefs;

import com.hiberus.university.mario.maven.first.Pages.CarritoPages;
import com.hiberus.university.mario.maven.first.Pages.LoginPage;
import com.hiberus.university.mario.maven.first.Pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CarritoPageSteps {
    PageFactory pageFactory= PageFactory.getInstance();
    CarritoPages carritoPages = pageFactory.getCarritoPages();
    LoginPage loginPage = pageFactory.getLoginPage();
    @Given("the user is logged successfully and is into the inventory")
    public void theUserIsLoggedSuccessfullyAndIsIntoTheInventory() {
        loginPage.login("standard_user","secret_sauce");

    }
    @When("the user adds {int} products to the inventory")
    public void theUserAddsProductsToTheInventory(int numero) {
        carritoPages.aniadir2productos(2);
    }

    @And("the user clicks on the cart")
    public void theUserClicksOnTheCart() {
        carritoPages.clickCarrito();
    }

    @And("the user removes {int} of these products from the cart")
    public void theUserRemovesOfTheseProductsFromTheCart(int numeroProductos) {
        carritoPages.eliminarProductoCarrito(numeroProductos);
    }

    @Then("the user should not be able to see the product in the cart")
    public void theUserShouldNotBeAbleToSeeTheProductInTheCart() {
        Assert.assertEquals("1",carritoPages.obtenerNumeroCarrito());

    }
}
