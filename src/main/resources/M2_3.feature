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
Feature: Client access to internal status

  @tag1
  Scenario: Successful filtering
    Given a client "Novo Nordisk" who owns the containers journeys
    And all the containers assigned to "Novo Nordisk"
    And the containers destination
    And the containers content
    And the containers start location
    When the client filters the containers journeys based on the specific destination
    Then the clients containers journeys with the specific destination are listed

    
   Scenario: Unsuccessful filtering
    Given a client "Microsoft" who owns the containers journeys
    And all the containers assigned to "Novo Nordisk"
    And the containers destination
    And the containers content
    And the containers start location
    When the client filters the containers journeys based on the specific destination
    Then the containers journeys with the specific destination are not listed
    
   Scenario: Unuccessful filtering
    Given a client "Novo Nordisk" who owns the containers journeys
    And all the containers assigned to "Novo Nordisk"
    And the containers destination
    And the containers content
    And the containers start location
    When the client filters the containers journeys based on the id
    Then the containers journeys with the specific destination are not listed

