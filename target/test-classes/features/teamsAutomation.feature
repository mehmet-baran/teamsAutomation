Feature: teams automation feature

  @teams
  Scenario: Waiting a meeting link is provided and once it's provided join the meeting when meeting ends wait for a new link
    Given the user waits for the meeting link and once it's provided navigates to that link
    When the user continues on browser instead of Teams application
    And the user should be able to join the meeting with a username
    Then the user should be able to stay in the meeting room until it's ended
    Then the user should be able to wait for a new meeting link

