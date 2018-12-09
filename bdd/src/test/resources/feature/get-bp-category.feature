Feature: Get Blood Pressure Category
  'Scenarios for getting valid and invalid Blood Pressure Categories.

  Scenario: Get Valid Blood Pressure Category
    Given browser is open on homepage
    And the systolic value is 75
    And the diastolic value is 45
    When i click submit
    Then it should display Low Blood Pressure.