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
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario: Succesful timestamp added at the beginning of the journey
  	Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    And the first client is a client of the the first logistics company
    When the first client requests to register a journey with the first logistics company with origin "Shenzhen", destination "Rotterdam" and content "medical goods" 
    And an id for the journey is created
    Then a beginning timestamp is added to mark the beginning of the journey 
    
  #Scenario: Failed to add timestamp at the beginning of the journey
  #	Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    #And a second logistics company "Hamburg Sud" with address "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany" reference person "Dr. Arnt Vespermann" and email "info@hamburgsud-line.com"
    #And a container of the first logistics company
    #And a second client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
    #And the second client is a client of the the second logistics company
    #When the second client requests to register a journey with the first logistics company with origin "Shenzhen", destination "Rotterdam" and content "medical goods" 
    #And an id for the journey is created
    #Then a beginning timestamp is added to mark the beginning of the journey 
    #
  #Scenario: Succesful timestamp added at the end of the journey
    #Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    #And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
#		And a container of the first logistics company in a journey and with an internal status
    #And the container has a location "New York"
    #When the first logistics company updates containers location to a new location "Atlantic Ocean"
    #Then then an ending timestamp is added to mark the end of the journey
    #And a journey map is assigned to the container 
    #
  #Scenario: Failed to add timestamp due to wrong company
    #Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" and email "info@maersk.com"
    #And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" and email "info@novonordisk.com"
#		And a container of the first logistics company in a journey and with an internal status
    #And the container has a beginning timestamp of a journey
    #When the second logistics company updates containers location to a new location "Atlantic Ocean"
    #Then then the timestamp is not added to mark the end of the journey
    #And a journey map is not assigned to the container 

