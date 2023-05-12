@CartSuite
Feature:Cart Test Suite

  Background:Cart
    Given the user is logged successfully and is into the inventory

  @DeleteProductToTheCart
  Scenario :Verify that the product is not in the cart
    When the user adds 2 products to the inventory
    And the user clicks on the cart
    And the user removes 1 of these products from the cart
    Then the user should not be able to see the product in the cart

