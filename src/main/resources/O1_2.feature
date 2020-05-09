@tag
Feature: Journey end

  Background: 
    Given a logged in logistics company with container and client with journey

  @tag1
  Scenario: Successful end of a journey
    Given the journey has started at 4:20 12/3/2020
    When the logistics company ends the journey with timestamp 4:20 13/3/2020
    Then the journey has ended
    And the ending timestamp of the journey is 4:20 13/3/2020
    And the logistics company fails to add a container status with timestamp 4:22 13/3/2020

  Scenario: Failed to end the journey because it is already ended
    Given the journey has started at 4:20 12/3/2020
    And the journey has ended at 4:20 13/3/2020
    When the logistics company ends the journey with timestamp 4:20 14/3/2020
    Then the journey failed to end
    And the ending timestamp of the journey is 4:20 13/3/2020

  Scenario: Failed to end the journey because it was not started
    When the logistics company ends the journey with timestamp 4:20 14/3/2020
    Then the journey failed to end

  Scenario: Failed to end the journey because the end timestamp is before the start timestamp
    Given the journey has started at 4:20 13/3/2020
    When the logistics company ends the journey with timestamp 4:20 12/3/2020
    Then the journey failed to end
    And the logistics company successfully adds a container status with timestamp 4:22 13/3/2020

  Scenario: Failed to end the journey because the end timestamp is before some of the status timestamps
    Given the journey has started at 4:20 12/3/2020
    And an initial container status in the journey of 5.0 degrees, 80.0 % humidity and 1.01 bar with a timestamp 4:20 14/3/2020
    When the logistics company ends the journey with timestamp 4:20 13/3/2020
    Then the journey failed to end
    And the logistics company successfully adds a container status with timestamp 4:22 13/3/2020
