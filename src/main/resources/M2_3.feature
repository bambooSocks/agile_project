@tag
Feature: Journey filtering by a keyword

  Background: 
    Given a logistics company with two containers and a logged in client with two journeys 

  @tag1
  Scenario: Successful filtering by destination
    When the first client filters his journeys based on the destination "New York"
    Then the first journey is listed

  Scenario: Successful filtering by origin

    When the first client filters his journeys based on the origin port "Copenhagen"
    Then the second journey is listed

  Scenario: Successful filtering by content
    When the first client filters his journeys based on the content "Kinder eggs"
    Then both journeys are listed
