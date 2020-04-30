@tag
Feature: Client searching

  Background: 
    Given an empty database
    And new application
    And a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"

  @tag1
  Scenario: Successful search using client name
    When a first logistics company searches for name "Chiquita"
    Then it exists and the client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123" is returned

  @tag2
  Scenario: Failed search using client name
    When a first logistics company searches for name "Dole"
    Then it does not exist and no client is returned

  @tag3
  Scenario: Successful search using address
    When a first logistics company searches for address "1855 Griffin Rd. Miami, Florida"
    Then it exists and the client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123" is returned

  @tag4
  Scenario: Failed search using address
    When a first logistics company searches for address "some address"
    Then it does not exist and no client is returned

  @tag5
  Scenario: Successful search using reference person
    When a first logistics company searches for reference person "Carmen Rodriguez"
    Then it exists and the client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123" is returned

  @tag6
  Scenario: Failed search using reference person
    When a first logistics company searches for reference person "Car7men nodriguez"
    Then it does not exist and no client is returned

  @tag7
  Scenario: Successful search using email
    When a first logistics company searches for email "bananas@chiquita.com"
    Then it exists and the client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123" is returned

  @tag8
  Scenario: Failed search using email
    When a first logistics company searches for email "fakeEmail@chiquita.com"
    Then it does not exist and no client is returned
