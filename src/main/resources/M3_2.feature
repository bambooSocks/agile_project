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
		Given a first logistic company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K", reference person "Soeren Skou" and email "info@maersk.com"
		And first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd", reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com" 
		And a container of first logistic company with ID 1
		And journey of given container and first client with origin port of "Shenzhen", destination port of "Rotterdam" and a content of "medical goods"   
		And an initial container status in the journey of 5.0 degrees, 80.0 % humidity and 1.01 bar
		When the first client requests access to the status
		Then a list of statuses is returned
		And a list of statuses contains a status of 5.0 degrees, 80.0 % humidity and 1.01 bar

	Scenario: Failed access because of wrong client in the journey
		Given a first logistic company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K", reference person "Soeren Skou" and email "info@maersk.com"
		And first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd", reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
		And a second client "Chiquita" with address "La Tuiliere, 16 1163 Etoy (VD) Switzerland", reference person "Brian W. Kocher" and email "info@chiquita.com"
		And a container of first logistic company with ID 1
		And journey of given container and first client with origin port of "Shenzhen", destination port of "Rotterdam" and a content of "medical goods"   
		And an initial container status in the journey of 5.0 degrees, 80.0 % humidity and 1.01 bar
		When the second client requests access to the status
		Then a list of statuses is not returned

		#And a second logistic company "Hamburg Sud" with address "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany", reference person "Dr. Arnt Vespermann" and email "info@hamburgsud-line.com"

