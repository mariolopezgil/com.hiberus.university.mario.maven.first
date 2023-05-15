package com.hiberus.university.mario.maven.first.StepDefs;

import com.hiberus.university.mario.maven.first.Pages.CarritoPages;
import com.hiberus.university.mario.maven.first.Pages.InventarioPages;
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
    LoginPage loginPage=pageFactory.getLoginPage();



    @Given("the user has logged in successfully")
    public void theUserHasLoggedInSuccessfully() {
        loginPage.navigateTo(LoginPage.PAGE_URL);
        loginPage.login("standard_user","secret_sauce");
    }
    @When("the user adds {int} products to the inventory")
    public void theUserAddsProductsToTheInventory(int numero) {
        carritoPages.aniadir2productos(2);
    }

    @And("the user is in the inventory page")
    public void theUserIsInTheInventoryPage() {
        Assert.assertEquals(InventarioPages.PAGE_URL,PageFactory.getInstance().getDriver().getCurrentUrl());
    }

    @And("the user clicks on the cart button")
    public void theUserClicksOnTheCartButton() {
        carritoPages.clickCarrito();
    }

    @And("the user removes {int} of the products from the cart")
    public void theUserRemovesOfTheProductsFromTheCart(int numero) {
        carritoPages.eliminarProductoCarrito(numero);
    }

    @Then("the user should not see the removed product in the cart")
    public void theUserShouldNotSeeTheRemovedProductInTheCart() {
        Assert.assertEquals("1",carritoPages.obtenerNumeroCarrito());
    }
}
