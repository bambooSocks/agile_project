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
Feature: Profile information update
Background:
Given an empty database
  @tag1
  Scenario: Successful client profile update
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    When a client enters new client info "Dole" with address "338 Hwy 82, Orlando, FLorida" reference person "Victor Krum" email "bigyellowbananas@chiquita.com" and password "Corndog12"
    Then the client "Dole" with address "338 Hwy 82, Orlando, FLorida" reference person "Victor Krum" email "bigyellowbananas@chiquita.com" and password "Corndog12" is successfully updated

  @tag2
  Scenario: Unsuccessful client profile update
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    When a client enters new client info "aChiquita7" with address "338 Hwy 82!, Orlando; FLorida" reference person "car7men Rodriguez Jr." email "bigyellowbananas.com" and password "lamepassword"
    Then the client "aChiquita7" with address "338 Hwy 82!, Orlando; FLorida" reference person "car7men Rodriguez Jr." email "bigyellowbananas.com" and password "lamepassword" is not updated

  @tag3
  Scenario: Successful logistics company profile update
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    When a logistics company enters new info "Hamburg-Sud" with address "338 Hwy 82, Orlando, FLorida" reference person "Victor Krum" email "bigyellowbananas@chiquita.com" and password "Corndog12"
    Then the logistics company "Hamburg-Sud" with address "338 Hwy 82, Orlando, FLorida" reference person "Victor Krum" email "bigyellowbananas@chiquita.com" and password "Corndog12" is successfully updated

  @tag4
  Scenario: Unsuccessful logistics company profile update
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    When a logistics company enters new info "hamburgSud?" with address "338 Hwy 82!, Orlando; FLorida" reference person "car7men Rodriguez Jr." email "bigyellowbananas.com" and password "lamepassword"
    Then the logistics company "hamburgSud?" with address "338 Hwy 82!, Orlando; FLorida" reference person "car7men Rodriguez Jr." email "bigyellowbananas.com" and password "lamepassword" is not updated
