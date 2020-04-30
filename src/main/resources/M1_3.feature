@tag
Feature: Profile information update

  @tag1
  Scenario: Successful client profile update
    Given a logistics company with logged in client
    When a client enters new client info "Dole" with address "338 Hwy 82, Orlando, FLorida" reference person "Victor Krum" email "bigyellowbananas@chiquita.com" and password "Corndog12"
    Then the new client data is "Dole" with address "338 Hwy 82, Orlando, FLorida" reference person "Victor Krum" email "bigyellowbananas@chiquita.com" and password "Corndog12"

  @tag2
  Scenario: Unsuccessful client profile update
    Given a logistics company with logged in client
    When a client enters new client info "aChiquita7" with address "338 Hwy 82!, Orlando; FLorida" reference person "car7men Rodriguez Jr." email "bigyellowbananas.com" and password "lamepassword"
    Then the client data has not been updated

  @tag3
  Scenario: Successful logistics company profile update
    Given a logged in logistics company
    When a logistics company enters new info "Hamburg-Sud" with address "338 Hwy 82, Orlando, FLorida" reference person "Victor Krum" email "bigyellowbananas@chiquita.com" and password "Corndog12"
    Then the new logistics company data is "Hamburg-Sud" with address "338 Hwy 82, Orlando, FLorida" reference person "Victor Krum" email "bigyellowbananas@chiquita.com" and password "Corndog12"

  @tag4
  Scenario: Unsuccessful logistics company profile update
    Given a logged in logistics company
    When a logistics company enters new info "hamburgSud?" with address "338 Hwy 82!, Orlando; FLorida" reference person "car7men Rodriguez Jr." email "bigyellowbananas.com" and password "lamepassword"
    Then the logistics company data has not been updated
