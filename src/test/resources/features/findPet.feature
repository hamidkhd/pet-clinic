Feature: Find Pet

  Scenario: A pet must be found with an ID if available.
    Given A pet with an ID.
    When Someone wants to find the bird with his ID.
    Then The pet is successfully found with his ID.
