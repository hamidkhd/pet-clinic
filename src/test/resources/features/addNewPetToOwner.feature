Feature: Add new pet to owner

  Scenario: A new pet must be added to owner.
    Given An owner.
    When The owner adds that new pet to his list.
    Then The pet must be successfully added to his pets list.
