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
Feature: Client profile creation

  @tag1
  Scenario: Successful new client profile creation
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    When the company creates a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
    Then an id is automatically generated
    And a new client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com" belongs to the company

  @tag2
  Scenario: Is new email valid
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    When the company creates a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananaschiquitacom"
    Then the email is not a valid email and the client is not created

  @tag3
  Scenario: Is new name valid
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    When the company creates a first client "Chiqu@7hfsoufahsdvhasædogihasdnflasædmogimaæfoi568jasd_lfas!d foijasdæfoiajsdfmæoiajsdmoiasjdfæita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
    Then the name is not a valid email and the client is not created
