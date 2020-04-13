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
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    When a client enters new client info "Chiquita" with address "338 Hwy 82, Orlando, FLorida" reference person "Carmen Rodriguez" email "bigyellowbananas@chiquita.com" and password "Object123"
    Then the client "Chiquita" with address "338 Hwy 82, Orlando, FLorida" reference person "Carmen Rodriguez" email "bigyellowbananas@chiquita.com" and password "Object123" is successfully updated
