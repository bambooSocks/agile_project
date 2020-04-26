@tag
Feature: Journey filtering by a keyword
Background:
Given an empty database
  @tag1
  Scenario: Successful filtering by destination
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And first client is logged-in with email "info@novonordisk.com" and password "Object123"
    And the first logistics company has two available containers
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "oranges"
    And a second journey of first client with origin port of "Copenhagen" destination port of "Amsterdam" and a content of "oranges"
    When the first client filters his journeys based on the destination "Rotterdam"
    Then the first journey is listed

  Scenario: Successful filtering by origin
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And first client is logged-in with email "info@novonordisk.com" and password "Object123"
    And the first logistics company has two available containers
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "oranges"
    And a second journey of first client with origin port of "Copenhagen" destination port of "Rotterdam" and a content of "oranges"
    When the first client filters his journeys based on the origin port "Copenhagen"
    Then the second journey is listed

  Scenario: Successful filtering by content
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And first client is logged-in with email "info@novonordisk.com" and password "Object123"
    And the first logistics company has two available containers
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "oranges"
    And a second journey of first client with origin port of "Copenhagen" destination port of "Rotterdam" and a content of "oranges"
    When the first client filters his journeys based on the content "oranges"
    Then both journeys are listed
