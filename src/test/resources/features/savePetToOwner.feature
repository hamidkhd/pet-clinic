Feature: Save new pet to owner

  Scenario: A new pet must be saved in owner pet list.
    Given An existing owner.
    And A existing pet.
    When The owner wants to save new pet in his list.
    Then The pet must be successfully added to his pets.
    And The pet must be successfully saved in his pets list.
