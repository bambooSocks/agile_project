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
  Scenario: Successful registration
    Given a container of "Maersk"
    And a client "Novo Nordisk" who owns the container journey
    And the container journey has start location "Copenhagen" and destination "New York"
    When a client requests to register the container
    Then the two locations are assigned to the containers journey
    And an Id is created

  Scenario: Container not
    Given a container already on a journey
    And a client "Novo Nordisk" who owns the container journey
    And the container journey has start location "Copenhagen" and destination "New York"
    When a client requests to register the container
    Then the two locations are not assigned to the containers journey
    And an Id is not created
