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
Feature: Log-ins and permissions

  @tag1
  Scenario: Successful Log-in
    Given first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
    When first client enters a valid password "Corndog12"
    Then first client is logged in

  @tag2
  Scenario: Client can view own containers
    Given first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
    And first client "Chiquita" is logged-in
    When client "Chiquita" tries to view "Chiquita" containers and data
    Then they can view it

  @tag3
  Scenario: Client can't view others containers
    Given first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
    And second client "Dole" with address "4 Privit Drive, Little Whinging" reference person "Dudley Dursley" and email "Ilovetoeat@hotmail.com"
    And first client "Chiquita" is logged-in
    When client "Chiquita" tries to view "Dole" containers and data
    Then they can not view it

  @tag4
  Scenario: Logistics Company can view own clients
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K", reference person "Soeren Skou" and email "info@maersk.com"
    And first logistics company "Maersk" is logged-in
    When logistics company "Maersk" tries to view "Maersk" clients, containers, and data
    Then they can view it

  @tag5
  Scenario: Logistics Company can't view other clients
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K", reference person "Soeren Skou" and email "info@maersk.com"
    And a second logistics company "Hamburg Sud" with address "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany", reference person "Dr. Arnt Vespermann" and email "info@hamburgsud-line.com"
    And first logistics company "Maersk" is logged-in
    When logistics company "Maersk" tries to view "Hamburg Sud" clients, containers, and data
    Then they can not view it

  @tag6
  Scenario: Client can give access to other clients
    Given first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" and email "bananas@chiquita.com"
    And first client "Chiquita" is logged-in
    When client "Chiquita" gives access to client "Dole"
    Then client "Dole" can view "Chiquita" clients, containers, and data
