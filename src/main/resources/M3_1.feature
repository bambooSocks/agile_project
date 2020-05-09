@tag
Feature: Container status tracking in container journey

  @tag1
  Scenario: Successful status entry
    Given a logged in logistics company with container and client with journey
    And the journey has started at 4:20 13/3/2020
    When the logistics company enters status 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:22 13/3/2020
    Then the journey contains the status
    And the journey is successfully updated

  Scenario: Different logistics company
    Given a logged in logistics company with container and client with journey
    And the journey has started at 4:20 13/3/2020
    And another logged in logistics company
    When the logistics company enters status 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:22 13/3/2020
    Then the journey does not contain the status
    And the journey has failed to update

  Scenario: Failed add status due to no available container
    Given a logged in logistics company with client and journey
    When the logistics company enters status 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:22 13/3/2020
    Then the journey has failed to update

  Scenario: Failed add status due to timestamp of container status being earlier than joruney start
    Given a logged in logistics company with container and client with journey
    And the journey has started at 4:20 13/3/2020
    When the logistics company enters status 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:10 13/3/2020
    Then the journey does not contain the status
    And the journey has failed to update
