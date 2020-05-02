@tag
Feature: Availability of containers based on end-date and start-date of the journey

  Background: 
    Given a logged in logistics company with a container and client with two journeys
    And the journey has started at 4:20 11/3/2020

  @tag1
  Scenario: Succesful start of second journey of container
    And the journey has ended at 4:20 12/3/2020
    When the logistics company starts a second journey with timestamp 4:20 13/3/2020
    Then the second journey has started
    And the container is not available at 4:25 13/3/2020
    And the list of journeys of the container contains the second journey

  Scenario: Failed to start journey due to last journey on the list not being ended
    When the logistics company starts a second journey with timestamp 4:20 13/3/2020
    Then the second journey failed to start
    And the list of journeys of the container does not contain the second journey

  Scenario: Failed to start journey due to the start-date being before the end-date of the last journey in the list
    And the journey has ended at 4:20 13/3/2020
    When the logistics company starts a second journey with timestamp 4:20 12/3/2020
    Then the second journey failed to start
    And the list of journeys of the container does not contain the second journey
