@CartSuite
Feature: Cart Test Suite

  Background: Cart
    Given that the user is on the home page
    And the user provides their username "username" and password "password"
    And the user clicks on the login button
    And the user is taken to the inventory page

  @DeleteProductToTheCart
  Scenario Outline : Verify that the product is not in the cart
    When the user adds 2 products to the inventory
    And the user clicks on the cart
    And the user removes 1 of these products from the cart
    Then the user should not be able to see the product in the cart

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |