@CheckOut
Feature: CheckOut Test Suite

  Background: User is logged in and has access to inventory
    Given the user has logged in successfully
    And the user is in the inventory page

  @VerifyItemsPrices
  Scenario Outline: Verify prices of products in checkout match those in inventory
    When the user adds 3 products to the cart
    And the user clicks on the cart button
    And the user clicks on the checkout button
    And the user fills out the form with name "<name>", lastname "<lastname>", and COD "<cod>"
    And the user clicks on the continue button
    Then the user should verify that the total price of items in the checkout matches the sum of individual prices in the inventory

    Examples:
     | name | lastname | cod |
     |  a   |     a     |     1    |

    @ConfirmationMessage
  Scenario Outline: Place an order and receive confirmation message
    When the user adds 1 product to the cart
    And the user clicks on the cart button
    And the user clicks on the checkout button
    And the user fills out the form with name "<name>", lastname "<lastname>", and COD "<cod>"
    And the user clicks on the continue button
    And the user clicks on the finish button
    Then the user should see a confirmation message that the order has been placed
    Examples:
     | name | lastname | cod |
     |  a   |     a     |     1    |
