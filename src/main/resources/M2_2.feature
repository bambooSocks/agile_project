@tag
Feature: Location update

  @tag1
  Scenario: Successful update
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And first logistics company is logged-in with email "info@maersk.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a container of the first logistics company
    And the container has a location "New York"
    When the first logistics company updates containers location to a new location "Atlantic Ocean"
    Then the location is changed

  Scenario: Different logistics companies
    Given a first logistics company "Maersk" with address "Esplanaden 50, 1098 Koebenhavn K" reference person "Soeren Skou" email "info@maersk.com" and password "Agile123"
    And a second logistics company "Hamburg Sud" with address "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany" reference person "Dr. Arnt Vespermann" email "info@hamburgsud-line.com" and password "Agile123"
    And second logistics company is logged-in with email "info@hamburgsud-line.com" and password "Agile123"
    And a first client "Novo Nordisk" with address "Novo Alle, 2880 Bagsvaerd" reference person "Lars Fruergaard Joergensen" email "info@novonordisk.com" and password "Object123"
    And a container of the first logistics company
    And the container has a location "Los Angeles"
    When the second logistics company updates containers location to a new location "Pacific Ocean"
    Then the location is not changed
