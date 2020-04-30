@tag
Feature: Client searching

  Background: 
    Given a logged in logistics company with client
    
  @tag1
  Scenario: Successful search using client name
    When a logistics company searches for name "Chiquita"
    Then the client of the logistics company is returned

  @tag2
  Scenario: Failed search using client name
    When a logistics company searches for name "Dole"
    Then no client is returned

  @tag3
  Scenario: Successful search using address
    When a logistics company searches for address "Miami, FL"
    Then the client of the logistics company is returned

  @tag4
  Scenario: Failed search using address
    When a logistics company searches for address "some address"
    Then no client is returned

  @tag5
  Scenario: Successful search using reference person
    When a logistics company searches for reference person "Banana Man"
    Then the client of the logistics company is returned

  @tag6
  Scenario: Failed search using reference person
    When a logistics company searches for reference person "Carmen nodriguez"
    Then no client is returned

  @tag7
  Scenario: Successful search using email
    When a logistics company searches for email "bananas@chiquita.com"
    Then the client of the logistics company is returned

  @tag8
  Scenario: Failed search using email
    When a logistics company searches for email "fakeEmail@chiquita.com"
    Then no client is returned
