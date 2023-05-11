Feature: CheckOut Test Suite

  Background: CheckOut
    Given the user is on the home page
    And the user provides the username "username" and password "password"
    And the user clicks the login button
    And the user is on the inventory page

  @VerifyItemsPrices
  Scenario Outline: Verify the price of products in the checkout are equal to the price of products in the inventory
    When the user adds 3 products
    And the user accesses the cart (clicks the cart button)
    And the user clicks the checkout button
    And the user fills out the form (inputs "name", "last name", "zip code")
    And the user clicks the continue button
    Then the user should be able to verify that the sum of the items they have added is equal to the amount shown on the checkout page under "items price"

    Examples:
      | username     | password     | name | last name | zip code |
      | standard_user| secret_sauce |  a   |     a     |     1    |

  @ConfirmationMessage
  Scenario Outline: Order
    When the user adds 3 products
    And the user accesses the cart (clicks the cart button)
    And the user clicks the checkout button
    And the user fills out the form (inputs "name", "last name", "zip code")
    And the user clicks the continue button
    And the user clicks the finish button
    Then the user should be able to see the confirmation message that the order has been placed

    Examples:
      | username     | password     | name | last name | zip code |
      | standard_user| secret_sauce |  a   |     a     |     1    |
