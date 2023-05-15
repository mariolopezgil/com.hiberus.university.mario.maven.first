Feature: Cart Test Suite

  Background: User is logged in and has access to inventory
    Given the user has logged in successfully
    And the user is in the inventory page

  @DeleteProductToTheCart
  Scenario: Verify that a product can be removed from the cart
    When the user adds 2 products to the cart
    And the user clicks on the cart button
    And the user removes 1 of the products from the cart
    Then the user should not see the removed product in the cart

