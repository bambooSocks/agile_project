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
Feature: Availability of containers based on end-date and start-date of the journey

  @tag1
  Scenario: Succesful start of second journey
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    And the first journey has started at 4:20 11/3/2020
    And the first journey has ended at 4:20 12/3/2020
    And the list of journeys of the container contains the first journey
    And the last journey of the container list is ended
    And a second journey of first client with origin port of "Rotterdam" destination port of "Copenhagen" and a content of "medical goods"
    When the logistics company starts a second journey of the first client with a timestamp 4:20 13/3/2020
    Then the list of journeys of the container contains the second journey
