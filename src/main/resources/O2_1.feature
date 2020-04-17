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
  Scenario: Successful client Log-in
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    When first client enters email "bananas@chiquita.com" and password "Object123"
    Then the client is logged in

  # Client enters wrong password scenario
  #  Scenario: Successful company Log-in
  @tag2
  Scenario: Client can view own containers
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    And first client is logged-in
    When client with email "bananas@chiquita.com" tries to view containers and data of client with email "bananas@chiquita.com"
    Then the containers and data can be viewed

  @tag3
  Scenario: Client cannot view others containers
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    And a second client "Dole" with address "4 Privit Drive, Little Whinging" reference person "Dudley Dursley" email "Ilovetoeat@hotmail.com" and password "Object123"
    And first client is logged-in
    When client with email "bananas@chiquita.com" tries to view containers and data of client with email "Ilovetoeat@hotmail.com"
    Then the containers and data can not be viewed

  @tag4
  Scenario: Logistics Company can view own clients
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in
    When logistics company with email "info@maersk.com" tries to view clients, containers, and data of logistics company with email "info@maersk.com"
    Then the clients, containers, and data can be viewed

  @tag5
  Scenario: Logistics Company can't view other clients
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a second logistics company "Hamburg Sud" with address "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany" reference person "Dr. Arnt Vespermann" email "info@hamburgsud-line.com" and password "Agile123"
    And first logistics company is logged-in
    When logistics company with email "info@maersk.com" tries to view clients, containers, and data of logistics company with email "info@hamburgsud-line.com"
    Then the clients, containers, and data can not be viewed

  @tag6
  Scenario: Client can give access to other clients
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    And a second client "Dole" with address "4 Privit Drive, Little Whinging" reference person "Dudley Dursley" email "Ilovetoeat@hotmail.com" and password "Object123"
    And first client is logged-in
    When first client with email "bananas@chiquita.com" gives access to client with email "Ilovetoeat@hotmail.com"
    Then they can view the containers and data of the first client
