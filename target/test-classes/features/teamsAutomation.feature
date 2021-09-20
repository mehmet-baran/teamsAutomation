Feature: teams automation feature

  @teamsWebsite
  Scenario: Joining a meeting through teams homepage
    Given the user logs in through teams website
    When the user clicks on Activity tab
    Then the user should be able to join the meeting

  @teamsLinkOneMeeting
  Scenario: Joining a meeting by using a meeting link
    Given the user navigates to the meeting link
    When the user continues on browser instead of Teams application
    Then the user should be able to join the meeting with a username

  @teamsLinkContinuousWaiting
  Scenario: Waiting a meeting link is provided and once it's provided join the meeting
    Given the user waits for the meeting link
    When the meeting link is provided the user navigates to the meeting link
    And the user continues on browser instead of Teams application
    Then the user should be able to join the meeting with a username

    @cli
    Scenario: cli
      Given reach to cli and execute the code