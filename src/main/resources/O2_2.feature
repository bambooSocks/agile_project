@tag
Feature: Viewing and sharing journeys

  Background: 
    Given an empty database
    And new application
    And a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    And a second client "Dole" with address "4 Privit Drive, Little Whinging" reference person "Dudley Dursley" email "Ilovetoeat@hotmail.com" and password "Object123"
    And a container of the first logistics company

  @tag1
  Scenario: Client can view own journeys
    And first client is logged-in with email "bananas@chiquita.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    And user is logged out
    When client with email "bananas@chiquita.com" tries to view containers and data of client with email "bananas@chiquita.com"
    Then the containers and data can be viewed

  @tag2
  Scenario: Client cannot view others journeys
    And first client is logged-in with email "bananas@chiquita.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    And user is logged out
    When client with email "bananas@chiquita.com" tries to view containers and data of client with email "Ilovetoeat@hotmail.com"
    Then the containers and data can not be viewed

  @tag3
  Scenario: Successful journey sharing with another client
    And first client is logged-in with email "bananas@chiquita.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    When first client shares journey with second client
    Then second client can view the journey of the first client

  @tag4
  Scenario: Logistics Company can see all of its clients data
    When first logistics company views its client list of "Chiquita" and "Dole"
    Then it can see both clients and their data
