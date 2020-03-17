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
Feature: Container status tracking in container journey

  @tag1
  Scenario: Successful status entry
    Given a container of "Maersk" in a container journey
    And a logistic company "Maersk"
    When a logistic company enters a container status of 5 degrees, 80 % humidity and 1.01 bar to the container journey
    Then a journey contains status 5 degrees, 80 % humidity and 1.01 bar
    And a journey is successfully updated
    
  Scenario: Different logistic company
    Given a container of "Hamburg Sud" in a container journey
    And a logistic company "Maersk"
    When a logistic company enters a container status of 5 degrees, 80 % humidity and 1.01 bar to the container journey
    Then a journey does not contain status 5 degrees, 80 % humidity and 1.01 bar
    And a journey is failed to update
    
  Scenario: Missing container in a journey
  	Given a container journey without a container
  	And a logistic company "Maersk"
  	When a logistic company enters a container status of 5 degrees, 80 % humidity and 1.01 bar to the container journey
  	Then a journey does not contain status 5 degrees, 80 % humidity and 1.01 bar
    And a journey is failed to update
    
    
    
