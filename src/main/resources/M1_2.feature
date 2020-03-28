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
Feature: Client searching

  @tag1
  Scenario: Successful search using email
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 K�benhavn K" reference person "S�ren Skou" and email "info@maersk.com"
    When a first logistics company searches for parameter "bananas@chiquita.com" in client profile
    And parameter "bananas@chiquita.com" exists in client profile
    Then the client "Chiquita" with address "1855 Griffin Road Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and ID 20031 is returned
 
  @tag2
  Scenario: Failed search using email
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 K�benhavn K" reference person "S�ren Skou" and email "info@maersk.com"
    When a first logistics company searches for parameter "bananas@chiquita.com" in client profile
    And parameter "bananas@chiquita.com" does not exist in client profile
    Then display a message that the parameter does not exist

  @tag3
  Scenario: Successful search using name
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 K�benhavn K" reference person "S�ren Skou" and email "info@maersk.com"
    When a first logistics company searches for parameter "Chiquita" in client profile
    And parameter "Chiquita" exists in client profile
    Then the client "Chiquita" with address "1855 Griffin Road Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and ID 20031 is returned

  @tag4
  Scenario: Failed search using name
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 K�benhavn K" reference person "S�ren Skou" and email "info@maersk.com"
    When a first logistics company searches for parameter "Chiquita" in client profile
    And parameter "Chiquita" does not exist in client profile
    Then display a message that the parameter does not exist
