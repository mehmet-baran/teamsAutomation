Feature: teams automation feature

  @option1
  Scenario: Joining a meeting through teams homepage
    Given the user logs in through teams website
    When the user clicks on Activity tab
    Then the user should be able to join the meeting
    Then the user should be able to stay in the meeting room until it's ended
    Then the user should be able to wait for a new meeting link in idle condition

  @option2
  Scenario: Joining a meeting by using a meeting link
    Given the user navigates to the meeting link
    When the user continues on browser instead of Teams application
    Then the user should be able to join the meeting with a username

  @option3
  Scenario: Waiting a meeting link is provided and once it's provided join the meeting
    Given the user waits for the meeting link and once it's provided navigates to that link
    When the user continues on browser instead of Teams application
    And the user should be able to join the meeting with a username
    Then the user should be able to stay in the meeting room until it's ended
    Then the user should be able to wait for a new meeting link

