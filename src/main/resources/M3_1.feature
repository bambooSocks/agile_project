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
Feature: Container status tracking in container journey

  @tag1
	Scenario: Successful status entry
		Given a first logistic company "Maersk" with address "Esplanaden 50, 1098 København K", reference person "Søren Skou" and email "info@maersk.com"
		And a first client "Novo Nordisk" with address "Novo Allé, 2880 Bagsvaerd", reference person "Lars Fruergaard Jørgensen" and email "info@novonordisk.com"
		And a container of first logistic company with ID 1
		And a journey of given container and first client with origin port of "Shenzhen", destination port of "Rotterdam" and a content of "medical goods"
		And a container status of 5 degrees, 80 % humidity and 1.01 bar to the given journey
		When the first logistic company enters the given container status
		Then the journey contains the given status
		And the journey is successfully updated

	Scenario: Different logistic company
		Given a first logistic company "Maersk" with address "Esplanaden 50, 1098 København K", reference person "Søren Skou" and email "info@maersk.com"
		And a second logistic company "Hamburg Sud" with address "Willy-Brandt-Straße 59, 20457 Hamburg, Germany", reference person "Dr. Arnt Vespermann" and email "info@hamburgsud-line.com"
		And a first client "Novo Nordisk" with address "Novo Allé, 2880 Bagsvaerd", reference person "Lars Fruergaard Jørgensen" and email "info@novonordisk.com"
		And a container of first logistic company with ID 1
		And a journey of given container and first client with origin port of "Shenzhen", destination port of "Rotterdam" and a content of "medical goods"
		And a container status of 5 degrees, 80 % humidity and 1.01 bar to the given journey
		When the second logistic company enters the given container status
		Then the journey does not contain the given status
		And the journey has failed to update

	Scenario: Missing container in a journey
		Given a first logistic company "Maersk" with address "Esplanaden 50, 1098 København K", reference person "Søren Skou" and email "info@maersk.com"
		And a first client "Novo Nordisk" with address "Novo Allé, 2880 Bagsvaerd", reference person "Lars Fruergaard Jørgensen" and email "info@novonordisk.com"
		And a journey of no container and first client with origin port of "Shenzhen", destination port of "Rotterdam" and a content of "medical goods"
		And a container status of 5 degrees, 80 % humidity and 1.01 bar to the given journey
		When the first logistic company enters the given container status
		Then the journey does not contain the given status
		And the journey has failed to update

