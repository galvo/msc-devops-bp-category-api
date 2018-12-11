Feature: Get Blood Pressure Category
  'Scenarios for getting valid and invalid Blood Pressure Categories.

  Scenario Outline: Get <display> from systolic value of <systolic> and diastolic value of <diastolic>
    Given browser is open on homepage
    And the systolic value is <systolic>
    And the diastolic value is <diastolic>
    When i click submit
    Then it should display <display>.

    @qa @dev @prod
    Examples:
      | systolic | diastolic | display                 |
      | 75       | 45        | Low Blood Pressure      |
      | 100      | 70        | Normal Blood Pressure   |
      | 130      | 85        | Pre-High Blood Pressure |
      | 160      | 95        | High Blood Pressure     |
      | 150      | 70        | Out of range            |

    @qa
    Examples:
      | systolic | diastolic | display                             |
      | 65       | 35        | Invalid Systolic & Diastolic Values |
      | 65       | 55        | Invalid Systolic Value              |
      | 130      | 110       | Invalid Diastolic Value             |
