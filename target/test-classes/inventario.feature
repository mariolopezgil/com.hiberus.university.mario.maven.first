@InventorySuite
Feature: Inventory Test Suite

  Background: Inventory
    Given the user is logged successfully and is into the inventory

  @Validation6Items
  Scenario : Verify that the inventory has 6 items
    When the user counts the number of items
    Then the user should be count 6 items




  @ValidationSauceLabsBoltT-Shirt
  Scenario Outline: Verify that the product "<product>" exists
    When the user navigates to the inventory page
    Then the user should be able to find the product "<product>"

    Examples:
      | product                |
     | Sauce Labs Bolt T-Shirt |

  @EliminateProductToTheInventory
  Scenario Outline: Verify that the product "<product>" is not in the cart
    When the user adds "<product>" to the cart
    And the user removes  "<product>" from the inventory
    Then the user should not be able to see the "<product>" in the cart

    Examples:
     | product |
     | Sauce Labs Bolt T-Shirt   |


  @add3Products
  Scenario: Verify that the number of cart items is 3
    When the user adds 3 products
    Then the user should be able to see that the number of cart items is 3



  @SortInventory
  Scenario Outline : Verify that the list of items is sorted by "<select>"
    When the user selects the option to sort the list of products by "<select>"
    Then the user should be able to see that the list of products is sorted by "<select>"


    Examples:
      | select |
      | az     |
      | lohi   |
      | hilo   |