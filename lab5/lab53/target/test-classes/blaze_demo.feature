Feature: Blaze Demo
  Scenario: Choose One Flight
    When I go to "http://www.blazedemo.com"
    And I choose "Paris" as departure
    And I choose "Buenos Aires" as destination
    And I find flights
    And I choose first flight
    And I purchase flight
    Then I should be shown flight
    