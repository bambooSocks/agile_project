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
    Given a logistic company "Maersk"
    And email "bananas@chiquita.com"
    When a logistic company searches for email "bananas@chiquita.com" in client profile
    And email "bananas@chiquita.com" exists
    Then the client "Chiquita" with address "1855 Griffin Road Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and ID 20031 is returned
 
  @tag2
  Scenario: Failed search using email
    Given a logistic company "Maersk"
    And email "bananas@chiquita.com"
    When a logistic company searches for email "bananas@chiquita.com" in client profile
    And email "bananas@chiquita.com" does not exist
    Then display a message that the email does not exist

  @tag3
  Scenario: Successful search using name
    Given a logistic company "Maersk"
    And name "Chiquita"
    When a logistic company searches for name "Chiquita" in client profile
    And name "Chiquita" exists
    Then the client "Chiquita" with address "1855 Griffin Road Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and ID 20031 is returned

  @tag4
  Scenario: Failed search using name
    Given a logistic company "Maersk"
    And name "Chiquita"
    When a logistic company searches for name "Chiquita" in client profile
    And name "Chiquita" does not exist
    Then display a message that the client does not exist
