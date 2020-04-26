@tag
Feature: Journey end
Background:
Given an empty database
  @tag1
  Scenario: Successful end of a journey
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    And the journey has started at 4:20 12/3/2020
    When the logistics company ends the first journey with a timestamp 4:20 13/3/2020
    Then the first journey has ended
    And the ending timestamp of the first journey is 4:20 13/3/2020
    And the logistics company fails to add a container status with a timestamp 4:22 13/3/2020

  Scenario: Failed to end the journey because it is already ended
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    And the first journey has started at 4:20 12/3/2020
    And the first journey has ended at 4:20 13/3/2020
    When the logistics company ends the first journey with a timestamp 4:20 14/3/2020
    Then the first journey failed to end
    And the ending timestamp of the first journey is 4:20 13/3/2020

  Scenario: Failed to end the journey because it was not started
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    When the logistics company ends the first journey with a timestamp 4:20 14/3/2020
    Then the first journey failed to end

  Scenario: Failed to end the journey because the end timestamp is before the start timestamp
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    And the first journey has started at 4:20 13/3/2020
    When the logistics company ends the first journey with a timestamp 4:20 12/3/2020
    Then the first journey failed to end
    And the logistics company successfully adds a container status with a timestamp 4:22 13/3/2020

  Scenario: Failed to end the journey because the end timestamp is before some of the status timestamps
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a container of the first logistics company
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    And the first journey has started at 4:20 12/3/2020
    And an initial container status in the journey of 5.0 degrees, 80.0 % humidity and 1.01 bar with a timestamp 4:20 14/3/2020
    When the logistics company ends the first journey with a timestamp 4:20 13/3/2020
    Then the first journey failed to end
    And the logistics company successfully adds a container status with a timestamp 4:22 13/3/2020

  Scenario: Failed to end the journey because the journey is missing
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    When the logistics company ends the first journey with a timestamp 4:20 12/3/2020
    Then the first journey failed to end
    And the logistics company fails to add a container status with a timestamp 4:22 13/3/2020
