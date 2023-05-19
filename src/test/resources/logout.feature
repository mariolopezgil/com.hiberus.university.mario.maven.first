@LogOut
Feature: LogOut Test Suite

  Background: User is logged in and has access to inventory
    Given the user has logged in successfully
    And the user is in the inventory page

  @VerifyLogOut
  Scenario: Verify that the user can log out
    When the user clicks on the account dropdown menu
    And the user selects the log out option
    Then the user should be redirected to the login page
