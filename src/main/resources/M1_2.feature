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
Feature: Client searching

  @tag1
  Scenario: Successful search using email
    Given a logistics company "Maersk"
    And an email of bananas@chiquita.com
    When a logistics company searches for bananas@chiquita.com in client profile
    And bananas@chiquita.com exists
    Then the client profile "Chiquita" is returned

  @tag2
  Scenario: Failed search for a client
    Given a logistics company "Maersk"
    And an email of bananas@chiquita.com
    When a logistics company searches for bananas@chiquita.com in client profile
    And bananas@chiquita.com does not exist
    Then display a message that the email does not exist

  @tag3
  Scenario: Successful search using name
    Given a logistics company "Maersk"
    And a client name of "Chiquita"
    When a logistics company searches for "Chiquita" in client profile
    And "Chiquita" exists
    Then the client profile "Chiquita" is returned

  @tag4
  Scenario: Failed search using name
    Given a logistics company "Maersk"
    And a client name of "Chiquita"
    When a logistics company searches for "Chiquita" in client profile
    And "Chiquita" does not exists
    Then display a message that the client does not exist
