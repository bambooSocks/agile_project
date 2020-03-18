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
  Scenario: Successful access
    Given a container of "Maersk" in a container journey
    And a client "Novo Nordisk" owns the container journey
    And a logistic company "Maersk"
    And the container journey has a status of 5 degrees, 80 % humidity and 1.01 bar
    When a client requests access to the status
    Then a list of statuses contains a status of 5 degrees, 80 % humidity and 1.01 bar
    And a list of statuses is returned 


