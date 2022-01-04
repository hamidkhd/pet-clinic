Feature: Find Owner

  Scenario: An owner must be found with an ID if available.
    Given A Owner with an ID.
    When Someone wants to find him with his ID.
    Then The owner is successfully found with his ID.
