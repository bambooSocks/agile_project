@tag
Feature: Logging in

  Background: 
    Given a logged in logistics company with client
    And user is logged out

  @tag1
  Scenario: Successful client Log-in
    When first client enters email "bananas@chiquita.com" and password "Object123"
    Then the client is logged in

  @tag2
  Scenario: Failed client Log-in - wrong email
    When first client enters email "blabla@chiquita.com" and password "Object123"
    Then the client is not logged in

  @tag3
  Scenario: Failed client Log-in - wrong password
    When first client enters email "bananas@chiquita.com" and password "Corndog12"
    Then the client is not logged in

  @tag4
  Scenario: Successful company Log-in
    When first logistics company enters email "bigboats@maersk.com" and password "Agile123"
    Then the company is logged in

  @tag5
  Scenario: Failed company Log-in - wrong email
    When first logistics company enters email "bla@maersk.com" and password "Agile123"
    Then the company is not logged in

  @tag6
  Scenario: Failed company Log-in - wrong password
    When first logistics company enters email "info@maersk.com" and password "wrongpassword123"
    Then the company is not logged in
