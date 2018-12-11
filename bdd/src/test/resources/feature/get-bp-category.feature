Feature: Get Blood Pressure Category
  'Scenarios for getting valid and invalid Blood Pressure Categories.

  Scenario: Get Valid Low Blood Pressure Category
    Given browser is open on homepage
    And the systolic value is 75
    And the diastolic value is 45
    When i click submit
    Then it should display Low Blood Pressure.

  Scenario: Get Valid Normal Blood Pressure Category
    Given browser is open on homepage
    And the systolic value is 100
    And the diastolic value is 70
    When i click submit
    Then it should display Normal Blood Pressure.

  Scenario: Get Valid Pre-High Blood Pressure Category
    Given browser is open on homepage
    And the systolic value is 130
    And the diastolic value is 85
    When i click submit
    Then it should display Pre-High Blood Pressure.

  Scenario: Get Valid High Blood Pressure Category
    Given browser is open on homepage
    And the systolic value is 160
    And the diastolic value is 95
    When i click submit
    Then it should display High Blood Pressure.

  Scenario: Get Out of range error for invalid systolic & diastolic values
    Given browser is open on homepage
    And the systolic value is 150
    And the diastolic value is 70
    When i click submit
    Then it should display Out of range.
