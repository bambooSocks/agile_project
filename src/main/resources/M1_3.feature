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
    Given a client of "Chiquita"
    And a client of "Chiquita" exists in client profile
    When a client enters new information of "info"
    Then the client profile information is changed to "info"
    And the client profile is successfully updated
	
	@tag2
  Scenario: Client profile name cannot be changed
    Given a client of "Chiquita"
    And a client of "Chiquita" exists in client profile
    When a client enters a new name
    Then display a message that the client name cannot be changed
    And the client profile is failed to update
    
  @tag3
  Scenario: Client profile id cannot be changed
    Given a client of "Chiquita"
    And a client of "Chiquita" exists in client profile
    When a client enters a new id
    Then display a message that the client id cannot be changed
    And the client profile is failed to update