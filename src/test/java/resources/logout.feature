@LogOut
Feature: LogOut Test Suite

  Background: LogOut
    Given that the user is on the home page
    And the user provides their username "username" and password "password"
    And the user clicks on the login button
    And the user is taken to the inventory page

  @VerifyLogOut
  Scenario Outline: Verify that the user can log out
    When the user clicks on the dropdown menu
    And the user selects and clicks on the logOut option
    Then the user should be taken to the login page

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |