@tag
Feature: Viewing and sharing journeys

  Background: 
    Given a logged in logistics company with container and client with journey
    And a second client "Dole" with address "4 Privit Drive, Little Whinging" reference person "Dudley Dursley" email "Ilovetoeat@hotmail.com" and password "Object123"

  @tag1
  Scenario: Client can view own journeys
    And user is logged out
    When client with email "bananas@chiquita.com" tries to view containers and data of client with email "bananas@chiquita.com"
    Then the containers and data can be viewed

  @tag2
  Scenario: Client cannot view others journeys
    And user is logged out
    When client with email "bananas@chiquita.com" tries to view containers and data of client with email "Ilovetoeat@hotmail.com"
    Then the containers and data can not be viewed

  @tag3
  Scenario: Successful journey sharing with another client
    When first client shares journey with second client
    Then second client can view the journey of the first client

  @tag4
  Scenario: Logistics Company can see all of its clients data
    When first logistics company views its client list of "Chiquita" and "Dole"
    Then it can see both clients and their data
