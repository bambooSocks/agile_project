@tag
Feature: Location update
Background:
Given an empty database
And new application
  @tag1
  Scenario: Successful update
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
		And a container of the first logistics company
		And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
		And the journey has started at 4:20 12/3/2020
    And the container entered location "New York" at 4:20 13/3/2020
    When the first logistics company updates containers location
    Then the location is changed

  Scenario: Different logistics companies
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a second logistics company "Hamburg Sud" with address "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany" reference person "Dr. Arnt Vespermann" email "info@hamburgsud-line.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Agile12322"
		And a container of the first logistics company
    And a first journey of first client with origin port of "Shenzhen" destination port of "Rotterdam" and a content of "medical goods"
    And the journey has started at 4:20 12/3/2020
    And the container entered location "Los Angeles" at 4:20 13/3/2020
    When the second logistics company updates containers location
    Then the location is not changed
