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
		Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
		And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
		And a container of the first logistics company
		And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
		And a container status of 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:22 13/3/2020
		And the journey has started at 4:20 13/3/2020  
		When the first logistics company enters the given container status
		Then the journey contains the given status
		And the journey is successfully updated

	Scenario: Different logistics company
		Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
		And a second logistics company "Hamburg Sud" with address "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany" reference person "Dr. Arnt Vespermann" and email "info@hamburgsud-line.com"
		And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
		And a container of the first logistics company
		And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
		And a container status of 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:22 13/3/2020
		And the journey has started at 4:20 13/3/2020
		When the second logistics company enters the given container status
		Then the journey does not contain the given status
		And the journey has failed to update

	Scenario: Failed add status due to no available container and journey is null
		Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
		And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
		And a container status of 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:22 13/3/2020
		And the journey has started at 4:20 13/3/2020
		When the first logistics company enters the given container status
		Then the journey has failed to update

	Scenario: Failed add status due to timestamp of container status being earlier than joruney start
		Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
		And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
		And a container of the first logistics company
		And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
		And a container status of 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:10 13/3/2020
		And the journey has started at 4:20 13/3/2020  
		When the first logistics company enters the given container status
		Then the journey does not contain the given status
		And the journey has failed to update
	