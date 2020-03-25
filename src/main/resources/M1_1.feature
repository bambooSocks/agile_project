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
Feature: Client profile creation

  @tag1
  Scenario: Successful new client profile creation
    Given a logistic company "Maersk" with address "Esplanaden 50, 1098 København K" reference person "Søren Skou" and email "info@maersk.com"
    And a client "Chiquita"
    And no client "Chiquita" exists in client profile
    When a logistic company enters client data of "Chiquita", "1855 Griffin Rd. Miami, Florida", "Carmen Rodriguez" and "bananas@chiquita.com" to the client profile
    Then id is automatically generated
    And a client profile contains "Chiquita", "1855 Griffin Road Miami, Florida", "Carmen Rodriguez", "bananas@chiquita.com" and 20031
    And a new client profile is successfully created

  @tag2
  Scenario: Client profile already exists
    Given a logistic company "Maersk" with address "Esplanaden 50, 1098 København K" reference person "Søren Skou" and email "info@maersk.com"
    And a client "Chiquita"
    And a client "Chiquita" exists in client profile
    Then display a message that the client already exists

  @tag3
  Scenario: Client tries to create profile
    Given no logistic company
    And a client "Chiquita"
    Then display a message that only a logistic company may create a client profile