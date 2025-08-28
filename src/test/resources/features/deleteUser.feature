Feature: Delete Feature
  This feature is to delete the employees in the urbuddi portal.

  Scenario: Delete a  user
    When User open the URL from config
    And User Enters Email and password from config
    And Click on Login button.
    When Click on Employee icon and delete the employee
    Then Employee should be delete succesfully.
