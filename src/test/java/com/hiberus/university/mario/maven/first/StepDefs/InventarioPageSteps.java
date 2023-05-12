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
    @Given("the user is logged successfully and is into the inventory")
    public void theUserIsLoggedSuccessfullyAndIsIntoTheInventory() {
        loginPage.login("standard_user","secret_sauce");

    }
    @When("the user counts the number of items")
    public int theUserCountsTheNumberOfItems() {
        return inventarioPages.obtenerNumeroElementos();
    }
    @Then("the user should be able to see that the number of cart items is {int}")
    public void theUserShouldBeAbleToSeeThatTheNumberOfCartItemsIs(int numero) {
        Assert.assertEquals(numero,theUserCountsTheNumberOfItems());

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
        inventarioPages.addSauceLabsEnabled(nombre);
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

    @And("the user removes  {string} from the inventory")
    public void theUserRemovesFromTheInventory(String nombre) {
        inventarioPages.clickRemove(nombre);
    }
}
