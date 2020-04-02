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
  	Given the first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K", reference person "Soeren Skou" and email "info@maersk.com"
    And the container of the first logistics company
    And the first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd", reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    When the first client requests to register the container for the journey with origin port of "Shenzhen", destination port of "Rotterdam" and a content of "medical goods" 
    And the first client requests to register the container for the the second journey with origin port of "New York", destination port of "Copenhagen" and a content of "masks"  
    Then an Id is created 

  Scenario: Out of containers
  	Given the first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K", reference person "Soeren Skou" and email "info@maersk.com"
    And the first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd", reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    When the first client requests to register no container for the journey with origin port of "Shenzhen", destination port of "Rotterdam" and a content of "medical goods" 
    Then the journey id is not created 
    
Scenario: Container not available
  	Given the first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K", reference person "Soeren Skou" and email "info@maersk.com"
    And the first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd", reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    And the container already in use 
    When the first client requests to register the container for the journey with origin port of "Shenzhen", destination port of "Rotterdam" and a content of "medical goods" 
    Then the journey id is not created 
    
    