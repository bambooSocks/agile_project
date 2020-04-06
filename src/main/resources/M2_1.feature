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
Feature: Container Registration

  @tag1
  Scenario: Successful registration
  	Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    And the first client is a client of the the first logistics company
    When the first client requests to register a journey with the first logistics company with origin "Shenzhen", destination "Rotterdam" and content "medical goods" 
    Then an id is created 

  Scenario: Out of containers
  	Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    And the first client is a client of the the first logistics company
    When the first client requests to register a journey with the first logistics company with origin "Shenzhen", destination "Rotterdam" and content "medical goods"  
    Then the journey doesnt exist
    
  Scenario: Not a client of logistics company
  	Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    When the first client requests to register a journey with the first logistics company with origin "Shenzhen", destination "Rotterdam" and content "medical goods"  
    Then the journey doesnt exist
    
    