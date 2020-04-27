@tag
Feature: Container status tracking in container journey
Background:
Given an empty database
And new application
  @tag1
  Scenario: Successful status entry
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a container of the first logistics company
    And first client is logged-in with email "info@novonordisk.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods" started at 4:20 13/3/2020
    And a container status of 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:22 13/3/2020
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    When the first logistics company enters the given container status
    Then the journey contains the given status
    And the journey is successfully updated

  Scenario: Different logistics company
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a second logistics company "Hamburg Sud" with address "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany" reference person "Dr. Arnt Vespermann" email "info@hamburgsud-line.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a container of the first logistics company
    And first client is logged-in with email "info@novonordisk.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods" started at 4:20 12/3/2020
    And a container status of 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:22 13/3/2020
    And second logistics company is logged-in with email "info@hamburgsud-line.com" and password "Agile123"
    When the second logistics company enters the given container status
    Then the journey does not contain the given status
    And the journey has failed to update

  Scenario: Failed add status due to no available container and journey is null
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a container status of 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:22 13/3/2020
    When the first logistics company enters the given container status
    Then the journey has failed to update

  Scenario: Failed add status due to timestamp of container status being earlier than joruney start
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a container of the first logistics company
    And first client is logged-in with email "info@novonordisk.com" and password "Object123"
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods" started at 4:20 13/3/2020
    And a container status of 5.0 degrees, 80.0 % humidity and 1.01 bar with timestamp 4:10 13/3/2020
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    When the first logistics company enters the given container status
    Then the journey does not contain the given status
    And the journey has failed to update
