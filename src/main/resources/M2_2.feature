@tag
Feature: Location update

  @tag1
  Scenario: Successful update
    Given a logged in logistics company with container and client with journey
    And the journey has started at 4:20 12/3/2020
    When the logistics company updates containers location to "New York" at 4:20 13/3/2020
    Then the location is changed

  Scenario: Different logistics companies
    Given a logged in logistics company with container and client with journey
    And the journey has started at 4:20 12/3/2020
    And another logged in logistics company
    When the second logistics company updates containers location to "Los Angeles" at 4:20 13/3/2020
    Then the location is not changed
