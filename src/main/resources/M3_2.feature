@tag
Feature: Client access to internal status
Background:
Given a logged in logistics company with container and client with journey

  @tag1
  Scenario: Successful access
  And the journey has started at 4:20 13/3/2020
    And an initial container status in the journey of 5.0 degrees, 80.0 % humidity and 1.01 bar with a timestamp 4:22 13/3/2020
    And first client is logged-in with email "info@novonordisk.com" and password "Object123"
    When the first client requests access to the status
    Then a list of statuses is returned
    And a list of statuses contains a status of 5.0 degrees, 80.0 % humidity and 1.01 bar with a timestamp 4:22 13/3/2020

  Scenario: Failed access because of wrong client in the journey
    And a second client "Chiquita" with address "La Tuiliere, 16 1163 Etoy (VD) Switzerland" reference person "Brian W. Kocher" email "info@chiquita.com" and password "Object123"
   And an initial container status in the journey of 5.0 degrees, 80.0 % humidity and 1.01 bar with a timestamp 4:22 13/3/2020
    And second client is logged-in with email "info@chiquita.com" and password "Object123"
    When the second client requests access to the status
    Then a list of statuses is not returned
