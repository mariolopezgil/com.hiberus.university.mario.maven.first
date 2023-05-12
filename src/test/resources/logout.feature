@LogOut
Feature: LogOut Test Suite

  Background: LogOut
    Given the user is logged successfully and is into the inventory

  @VerifyLogOut
  Scenario : Verify that the user can log out
    When the user clicks on the dropdown menu
    And the user selects and clicks on the logOut option
    Then the user should be taken to the login page

