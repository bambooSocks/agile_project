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
Feature: Location update

  @tag1
  Scenario: Successful update
    Given the first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K", reference person "Soeren Skou" and email "info@maersk.com"
    And the first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd", reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
		And the container of the first logistics company with ID 1
    And the container has a location 45.741895 93.98930
    When the first logistics company updates containers location
    Then the location is changed
    
  Scenario: Different logistics companies
    Given the first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K", reference person "Soeren Skou" and email "info@maersk.com"
    And the second logistic company "Hamburg Sud" with address "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany", reference person "Dr. Arnt Vespermann" and email "info@hamburgsud-line.com"
    And the first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd", reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
		And the container of the first logistics company with ID 1
    And the container has a location 45.741895 93.98930
    When the second logistics company updates containers location
    Then the location is not changed

