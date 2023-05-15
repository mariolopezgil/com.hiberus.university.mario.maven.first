package com.hiberus.university.mario.maven.first.StepDefs;

import com.hiberus.university.mario.maven.first.Pages.InventarioPages;
import com.hiberus.university.mario.maven.first.Pages.LoginPage;
import com.hiberus.university.mario.maven.first.Pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class InventarioPageSteps {
    PageFactory pageFactory= PageFactory.getInstance();
    InventarioPages inventarioPages= pageFactory.getInventarioPages();
    LoginPage loginPage= pageFactory.getLoginPage();
    @When("the user counts the number of items")
    public int theUserCountsTheNumberOfItems() {
        return inventarioPages.obtenerNumeroElementos();
    }
    @When("the user navigates to the inventory page")
    public void theUserNavigatesToTheInventoryPage() {
        Assert.assertEquals(InventarioPages.PAGE_URL,PageFactory.getInstance().getDriver().getCurrentUrl());
    }
    @Then("the user should be able to find the product {string}")
    public void theUserShouldBeAbleToFindTheProduct(String nombre) {
        Assert.assertTrue(inventarioPages.getCamiseta(nombre));
    }
    @When("the user adds {string} to the cart")
    public void theUserAddsToTheCart(String nombre) {
        inventarioPages.clickAniadir(nombre);
    }

    @Then("the user should not be able to see the {string} in the cart")
    public void theUserShouldNotBeAbleToSeeTheInTheCart(String nombre) {
        Assert.assertTrue(inventarioPages.addSauceLabsEnabled(nombre));
    }

    @When("the user adds {int} products")
    public void theUserAddsProducts(int numero) {
        inventarioPages.aniadir3productos(numero);
    }

    @When("the user selects the option to sort the list of products by {string}")
    public void theUserSelectsTheOptionToSortTheListOfProductsBy(String sort) {
        inventarioPages.sortMenu(sort);
    }

    @Then("the user should be able to see that the list of products is sorted by {string}")
    public void theUserShouldBeAbleToSeeThatTheListOfProductsIsSortedBy(String sort) {
        if(sort.equals("az")){
            Assert.assertEquals(inventarioPages.sortInventoryExpected(sort).get(0),inventarioPages.sortInvenory(sort).get(0));
        }else{
            Assert.assertEquals(inventarioPages.sortInventoryExpected(sort).get(1),inventarioPages.sortInvenory(sort).get(1));
        }
    }
    @Then("the user should be count {int} items")
    public void theUserShouldBeCountItems(int numeroItems) {
        Assert.assertEquals(numeroItems,theUserCountsTheNumberOfItems());
    }
    @Given("the user is logged in successfully and is on the inventory page")
    public void theUserIsLoggedInSuccessfullyAndIsOnTheInventoryPage() {
        inventarioPages.navigateTo(LoginPage.PAGE_URL);
        loginPage.login("standard_user","secret_sauce");
    }

    @Then("the user should see {int} items in the inventory")
    public void theUserShouldSeeItemsInTheInventory(int numero) {
        Assert.assertEquals(numero,theUserCountsTheNumberOfItems());

    }

    @And("the user removes {string} from the inventory")
    public void theUserRemovesFromTheInventory(String nombre) {inventarioPages.clickRemove(nombre);
    }

    @When("the user adds {int} products to the cart")
    public void theUserAddsProductsToTheCart(int numero) {
        inventarioPages.aniadir3productos(numero);
    }

    @Then("the user should see that the number of cart items is {int}")
    public void theUserShouldSeeThatTheNumberOfCartItemsIs(int numero) {
        Assert.assertEquals( String.valueOf(numero),inventarioPages.obtenerNumeroCarrito());

    }
}
