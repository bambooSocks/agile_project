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
Feature: Journey start

  @tag1
  Scenario: Successful journey start
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods" 
    When the logistics company starts a journey with a timestamp 4:20 13/3/2020
    Then the container is allocated
    And the journey is started
    And the starting timestamp of the journey is 4:20 13/3/2020
    And the logistics company successfully adds a container status with a timestamp 4:22 13/3/2020

	Scenario: Failed journey start because of journey already started
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    And the journey has started at 4:20 13/3/2020 
    When the logistics company starts a journey with a timestamp 4:20 14/3/2020
    Then the journey failed to start
    And the logistics company successfully adds a container status with a timestamp 4:22 13/3/2020
	
	Scenario: Failed journey start because of missing journey
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    When the logistics company starts a journey with a timestamp 4:20 14/3/2020
    Then the journey failed to start
    And the logistics company fails to add a container status with a timestamp 4:22 13/3/2020
	
	