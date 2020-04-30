@tag
Feature: Journey Registration

  @tag1
  Scenario: Successful registration
    Given a logistics company with container and logged in client
    When the client requests to register a journey with origin "Shenzhen", destination "Rotterdam" and content "medical goods"
    Then the journey is created

  Scenario: Unsuccessful registration due to client not logged in
    Given a logged in logistics company with client
    When the client requests to register a journey with origin "Shenzhen", destination "Rotterdam" and content "medical goods"
    Then the journey is not created
