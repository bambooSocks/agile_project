@tag
Feature: Client access to internal status

  Background: 
    Given a logged in logistics company with container and client with journey
    And the journey has started at 4:20 13/3/2020
    And an initial container status in the journey of 5.0 degrees, 80.0 % humidity and 1.01 bar with a timestamp 4:22 13/3/2020

  @tag1
  Scenario: Successful access
    Given the client is now logged in
    When the client requests access to the status
    Then a list of statuses is returned
    And a list of statuses contains a status of 5.0 degrees, 80.0 % humidity and 1.01 bar with a timestamp 4:22 13/3/2020

  Scenario: Failed access because of wrong client in the journey
    Given another client is now logged in
    When the client requests access to the status
    Then a list of statuses is not returned
