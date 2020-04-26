@tag
Feature: Container Registration
Background:
Given an empty database
  @tag1
  Scenario: Successful registration
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And first client is logged-in with email "info@novonordisk.com" and password "Object123"
    When the first client requests to register a journey with the first logistics company with origin "Shenzhen", destination "Rotterdam" and content "medical goods"
    Then an id is created

  Scenario: Out of containers
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And first client is logged-in with email "info@novonordisk.com" and password "Object123"
    When the first client requests to register a journey with the first logistics company with origin "Shenzhen", destination "Rotterdam" and content "medical goods"
    Then the journey doesnt start
