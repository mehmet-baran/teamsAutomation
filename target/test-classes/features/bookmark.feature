Feature: Bookmark feature


  @bookmark
  Scenario: As a user I should be able to see the bookmarked courses at dashboard

    Given the user logs in as "Student" role
    When the user navigates to "Programs" page
    And the user selects "Java Selenium SDET B12 Online" program
    And the user clicks bookmark icon for a course
    Then the course should been able to bookmarked

    Scenario: new scenario
    Given the user logs in as "Student" role
    When the user navigates to "Bookmarks" page
#    And the user selects "Java Selenium SDET B12 Online" program
#    And the user clicks bookmark icon for a course

