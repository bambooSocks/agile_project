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
    Given a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
    When a client enters new client info "Chiquita" with address "338 Hwy 82, Orlando, FLorida" reference person "Carmen Rodriguez" and email "bigyellowbananas@chiquita.com"
    Then the client profile is successfully updated

#  @tag2
#  Scenario: Client profile id cannot be changed
#    Given first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
#    And client "Chiquita" exists in client profile
#    When a client enters a new id 20354
#    Then display a message that the client id cannot be changed
#    And the client profile fails to update
