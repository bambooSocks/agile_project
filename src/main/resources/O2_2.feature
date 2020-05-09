@tag
Feature: Viewing and sharing journeys

  Background: 
    Given a logged in logistics company with container and client with journey
    And a second client "Dole" with address "4 Privet Drive, Little Whinging" reference person "Dudley Dursley" email "Ilovetoeat@hotmail.com" and password "Object123"

  @tag1
  Scenario: Successful journey sharing with another client
    When first client shares journey with second client
    Then second client can view the journey of the first client

  @tag2
  Scenario: Logistics Company can see all of its clients data
    When first logistics company views its client list of "Chiquita" and "Dole"
    Then it can see both clients and their data
