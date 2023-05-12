Feature: CheckOut Test Suite

  Background: CheckOut
    Given the user is logged successfully and is into the inventory

  @VerifyItemsPrices
  Scenario Outline: Verify the price of products in the checkout are equal to the price of products in the inventory
    When the user adds 3 products
    And the user accesses the cart (clicks the cart button)
    And the user clicks the checkout button
    And the user fills out the form (inputs "<name>", "<lastname>", "<cod>")
    And the user clicks the continue button
    Then the user should be able to verify that the sum of the items they have added is equal to the amount shown on the checkout page under items price

    Examples:
     | name | lastname | cod |
     |  a   |     a     |     1    |

  @ConfirmationMessage
  Scenario Outline: Order
    When the user adds 1 products
    And the user accesses the cart (clicks the cart button)
    And the user clicks the checkout button
    And the user fills out the form (inputs "<name>", "<last name>", "<zip code>")
    And the user clicks the continue button
    And the user clicks the finish button
    Then the user should be able to see the confirmation message that the order has been placed

    Examples:
     | name | last name | zip code |
     |  a   |     a     |     1    |
