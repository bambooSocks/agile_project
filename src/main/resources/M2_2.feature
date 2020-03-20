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
Feature: Location update

  @tag1
  Scenario: Successful update
    Given a container of "Maersk"
    And a logistics company "Maersk"
    And a client "Novo Nordisk" who owns the container journey
    And the container has a location 45.741895 93.98930
    When logistics company updates containers location
    Then the location is changed
    
   Scenario: Different logistics companies
    Given a container of "Hamburg Sud"
    And a logistics company "Maersk"
    And a client "Novo Nordisk" who owns the container journey
    And the container has a location 45.741895 93.98930
    When logistics company updates containers location
    Then the location is not changed
    
 #   Scenario: Different logistics companies
  #  Given unregistered container
   # And a logistics company "Maersk"
    #And a client "Novo Nordisk" who owns the container journey
   # And the container has a location 45.741895 93.98930
   # When logistics company updates containers location to 40.741895 73.989308
    #Then the location is not changed

