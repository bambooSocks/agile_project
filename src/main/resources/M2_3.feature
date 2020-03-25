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
  Scenario: Successful filtering
    Given the first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd", reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    When the first client filters his containers journeys based on the destination "Rotterdam"
    Then the clients containers journeys with the specific destination are listed


