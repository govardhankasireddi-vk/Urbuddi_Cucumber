Feature: Login Feature
  This feature is to login to the urbuddi portal.

  Background:
    Given Launch the broswer

  @smoke
  Scenario Outline: Login to portal
    When User open the URL: <URL>
    Then Title Should contains <string>
    When User Enters Email as <email> and password as <password>
    And Click on Login button.
    Then Page Should contains <button>
    And Close Browser

    Examples:
      | URL                        | string    | email                 | password   | button   |
      | "https://dev.urbuddi.com/" | "urBuddi" | "twl4admin@gmail.com" | "twl4test" | "Logout" |

  @sanity
  Scenario: Create a new user

    When User open the URL:
      | URL                      |
      | https://dev.urbuddi.com/ |
    And User Enters Email  and password
      | email               | password |
      | twl4admin@gmail.com | twl4test |
    And Click on Login button.
    When Click on Employee icon and add Employee
    Then Option to enter all the required fields should display
      | Firstname   | Lastname   | Phone      | email                  | empID       | department | designation | location  | qualification | gender1 | BloodGroup | dob        | jod        | reportingTo     | role |
      | firstRANDOM | LastRANDOM | Random_Num | mymailRANDOM@gmail.com | OWRandom_id | Quality    | Senior QA   | Hyderabad | PG            | Male    | B+         | 25-05-2005 | 04-08-2025 | xzjgn@gmail.com | HR   |
    And Click on add button
    Then Employee should be add succesfully.










