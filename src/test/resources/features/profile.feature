@profile
Feature: Profile functions

  @TA-140
  Scenario: 1. TA-140 As a user I should not be able to change my password by providing a mismatched new password
    Given "student" is on the "Profile" page
    When the user provides correct old password to change password
    And the user provides mismatched new password
    Then the user should get "Passwords didnt match" error

  @TA-141
  Scenario: 2. TA-141 As a user I should be able to see my history under History section on Profile page
    Given "student" is on the "Profile" page
    When the user clicks on "History" tab
    Then the user should be able to see personal history