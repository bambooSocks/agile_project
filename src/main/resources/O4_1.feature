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
Feature: Storing data when app is closing

  @tag1
  Scenario: App closed
    Given a client "Novo Nordisk" who owns the containers journeys
    And client "Novo Nordisk" containers journeys
    When the client wants to close the app
    Then the clients "Novo Nordisk" containers journeys are saved and the app is closed

	Scenario: App opened
    Given a client "Novo Nordisk" who owns the containers journeys
    When the client "Novo Nordisk" wants to open the app
    Then the clients "Novo Nordisk" containers journeys are loaded from the database
    
   
