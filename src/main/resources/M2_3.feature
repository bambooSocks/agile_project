@tag
Feature: Journey filtering by a keyword

  Background: 
    Given a logged in logistics company with client

  @tag1
  Scenario: Successful filtering by destination
    And the first logistics company has two available containers
    And first client is logged-in with email "bananas@chiquita.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "oranges"
    And a second journey of first client with origin port of "Copenhagen" destination port of "Amsterdam" and a content of "oranges"
    When the first client filters his journeys based on the destination "Rotterdam"
    Then the first journey is listed

  Scenario: Successful filtering by origin
    And the first logistics company has two available containers
    And first client is logged-in with email "bananas@chiquita.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "oranges"
    And a second journey of first client with origin port of "Copenhagen" destination port of "Rotterdam" and a content of "oranges"
    When the first client filters his journeys based on the origin port "Copenhagen"
    Then the second journey is listed

  Scenario: Successful filtering by content
    And the first logistics company has two available containers
    And first client is logged-in with email "bananas@chiquita.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "oranges"
    And a second journey of first client with origin port of "Copenhagen" destination port of "Rotterdam" and a content of "oranges"
    When the first client filters his journeys based on the content "oranges"
    Then both journeys are listed
