Feature: Get Blood Pressure Category
  'Scenarios for getting valid and invalid Blood Pressure Categories.

  Scenario: Get Valid Blood Pressure Category
    Given service is healthy
    And a GET request to /blah will respond with status 200 and payload:
          """

          """