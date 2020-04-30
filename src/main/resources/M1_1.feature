@tag
Feature: Client profile creation

  Background: 
    Given an empty database
    And new application
    And a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"

  @tag1
  Scenario: Successful new client profile creation
    When the company creates a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    Then an id is automatically generated
    And a new client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123" belongs to the company

  @tag2
  Scenario: Unsuccessful new client profile creation
    When the company creates a first client "chiqu!t@" with address "338 Hwy 82!, Orlando; FLorida" reference person "car7men Rodriguez Jr." email "bananas.com" and password "lamepassword"
    Then the information is not valid and the client is not created
