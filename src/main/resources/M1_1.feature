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
    Given a first logistic company "Maersk" with address "Esplanaden 50, 1098 København K" reference person "Søren Skou" and email "info@maersk.com"
    And first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
    And client "Chiquita" does not exist in client profile
    When the first logistic company enters client data
    Then an id is automatically generated
    And a client profile contains the client data and id
    And a new client profile is successfully created

  @tag2
  Scenario: Client profile already exists
    Given a first logistic company "Maersk" with address "Esplanaden 50, 1098 København K" reference person "Søren Skou" and email "info@maersk.com"
    And first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
    And client "Chiquita" exists in client profile
    Then display a message that the client already exists

  @tag3
  Scenario: Client tries to create profile
    Given no logistic company
    And first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
    Then display a message that only a logistic company may create a client profile