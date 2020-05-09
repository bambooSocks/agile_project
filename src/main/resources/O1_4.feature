@tag
Feature: Searching for clients and containers journeys

  Background: 
    Given a logged in logistics company with a container and a clients with two ended journeys

  @tag1
  Scenario: Successful filtering of clients journeys by destination
    When the company filters clients journeys based on the destination "New York"
    Then the first journey is in the filtered list

  Scenario: Successful filtering of clients journeys by origin
    When the company filters clients journeys based on the origin "Copenhagen"
    Then the second journey is in the filtered list

  Scenario: Successful filtering of clients journeys by content
    When the company filters clients journeys based on the content "Kinder eggs"
    Then both journeys are in the filtered list

  Scenario: Successful filtering of containers journeys by destination
    When the company filters containers journeys based on the destination "Stockholm"
    Then the second journey is in the filtered list

  Scenario: Successful filtering of containers journeys by origin
    When the company filters containers journeys based on the origin "Helsinki"
    Then none of the journeys is in the filtered list

  Scenario: Successful filtering of containers journeys by content
    When the company filters containers journeys based on the content "Kinder eggs"
    Then both journeys are in the filtered list