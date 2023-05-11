@InventorySuite
Feature: Inventory Test Suite

  Background: Inventory
    Given the user is on the home page
    And the user provides the username "username" and password "password"
    And the user clicks the login button
    And the user is on inventory page

  @Validation6Items
  Scenario Outline: Verify that the inventory has 6 items
    When the user counts the number of items
    Then the user should be count 6 items

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  @ValidationSauceLabsBoltT-Shirt
  Scenario Outline: Verify that the product "product" exists
    When the user navigates to the inventory page
    Then the user should be able to find the product "product"

    Examples:
      | username     | password     | product                |
      | standar_user | secret_sauce | Sauce Labs Bolt T-Shirt |

  @EliminateProductToTheInventory
  Scenario Outline: Verify that the product "product" is not in the cart
    When the user adds "product" to the cart
    And the user removes this product from the inventory
    Then the user should not be able to see the "product" in the cart

    Examples:
      | username     | password     | product |
      | standar_user | secret_sauce | Sauce Labs Bolt T-Shirt   |


  @add3Products
  Scenario Outline: Verify that the number of cart items is 3
    When the user adds 3 products
    Then the user should be able to see that the number of cart items is 3

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  @SortInventory
  Scenario Outline : Verify that the list of items is sorted by "select"
    When the user selects the option to sort the list of products by "select"
    Then the user should be able to see that the list of products is sorted by "select"


    Examples:
      | username     | password     | select |
      | standar_user | secret_sauce | az     |
      | standar_user | secret_sauce | lohi   |
      | standar_user | secret_sauce | hilo   |