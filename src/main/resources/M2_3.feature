#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Journey filtering by a keyword

  @tag1
  Scenario: Successful filtering by destination
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And the first logistics company has two available containers
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "oranges"
    And a second journey of first client with origin port of "Copenhagen" destination port of "Amsterdam" and a content of "oranges"
    When the first client filters his journeys based on the destination "Rotterdam"
    Then the first journey is listed

  Scenario: Successful filtering by origin
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And the first logistics company has two available containers
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "oranges"
    And a second journey of first client with origin port of "Copenhagen" destination port of "Rotterdam" and a content of "oranges"
    When the first client filters his journeys based on the origin port "Copenhagen"
    Then the second journey is listed

  Scenario: Successful filtering by content
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And the first logistics company has two available containers
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "oranges"
    And a second journey of first client with origin port of "Copenhagen" destination port of "Rotterdam" and a content of "oranges"
    When the first client filters his journeys based on the content "oranges"
    Then both journeys are listed
