@tag
Feature: Journey start

  Background: 
    Given a logistics company with container and logged in client

  @tag1
  Scenario: Successful journey start
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    And first logistics company is logged-in with email "bigboats@maersk.com" and password "Agile123"
    When the logistics company starts a first journey of the first client with a timestamp 4:20 13/3/2020
    Then the first journey has started
    And the starting timestamp of the first journey is 4:20 13/3/2020
    And the logistics company successfully adds a container status with a timestamp 4:22 13/3/2020

  Scenario: Failed journey start because of journey already started
    And first logistics company is logged-in with email "bigboats@maersk.com" and password "Agile123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods" started at 4:20 13/3/2020
    When the logistics company starts a first journey of the first client with a timestamp 4:20 14/3/2020
    Then the first journey failed to start
    And the logistics company successfully adds a container status with a timestamp 4:22 13/3/2020

  Scenario: Failed journey start because of missing journey
    And first logistics company is logged-in with email "bigboats@maersk.com" and password "Agile123"
    When the logistics company starts a first journey of the first client with a timestamp 4:20 14/3/2020
    Then the first journey failed to start
    And the logistics company fails to add a container status with a timestamp 4:22 13/3/2020
