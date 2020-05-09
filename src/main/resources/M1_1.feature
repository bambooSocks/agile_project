@tag
Feature: Client profile creation

  Background: 
    Given a logged in logistics company

  @tag1
  Scenario: Successful new client profile creation
    When the company creates a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    Then a new client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123" belongs to the company

  @tag2
  Scenario: Unsuccessful new client profile creation due to wrong input data
    When the company creates a first client "chiqu!t@" with address "338 Hwy 82!, Orlando; FLorida" reference person "car7men Rodriguez Jr." email "bananas.com" and password "lamepassword"
    Then the information is not valid and the client is not created

  @tag3
  Scenario: Unsuccessful new client profile creation due to duplicate email
    Given a client with email "bananas@chiquita.com"
    When the company creates a first client "Chiquita" with address "1855 Griffin Rd. Miami, Florida" reference person "Carmen Rodriguez" email "bananas@chiquita.com" and password "Object123"
    Then the information is not valid and the client is not created
