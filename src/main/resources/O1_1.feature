@tag
Feature: Journey start

  Background: 
    Given a logistics company with container and logged in client

  @tag1
  Scenario: Successful journey start
    Given a logged in logistics company with container and client with journey
    When the logistics company starts a journey with timestamp 4:20 13/3/2020
    Then the journey has started
    And the starting timestamp of the journey is 4:20 13/3/2020
    And the logistics company successfully adds a container status with timestamp 4:22 13/3/2020

  Scenario: Failed journey start because of journey already started
    Given a logged in logistics company with container and client with journey
    And the journey has started at 4:20 13/3/2020
    When the logistics company starts a journey with timestamp 4:20 14/3/2020
    Then the journey failed to start
    And the logistics company successfully adds a container status with timestamp 4:22 13/3/2020
