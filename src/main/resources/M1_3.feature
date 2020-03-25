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
Feature: Client profile update

  @tag1
  Scenario: Successful client profile update
    Given a client "Chiquita" with address "1855 Griffin Road Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and ID 20031
    And a client "Chiquita" exists in client profile
    When a client enters new address "338 Hwy 82, Orlando, FLorida" and email "bigyellowbananas@chiquita.com"
    Then the client profile is changed to address "338 Hwy 82, Orlando, FLorida" reference person "Carmen Rodriguez" email "bigyellowbananas@chiquita.com" and ID 20031
    And the client profile is successfully updated
	
  @tag2
  Scenario: Client profile id cannot be changed
    Given a client "Chiquita" with address "1855 Griffin Road Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and ID 20031
    And a client "Chiquita" exists in client profile
    When a client enters a new id 20354
    Then display a message that the client id cannot be changed
    And the client profile fails to update