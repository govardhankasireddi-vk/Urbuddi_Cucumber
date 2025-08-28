Feature: Add and Delete user Feature
  This feature is to add employee in the application and delete the employee.

  Scenario: Add and Delete user scenario
      When User open the URL from config
      And User Enters Email and password from config
      And Click on Login button.
      When Click on Employee icon and add Employee
      Then Option to enter all the required fields should display
      | Firstname   | Lastname   | Phone      | email                  | empID       | department | designation | location  | qualification | gender1 | BloodGroup | dob        | jod        | reportingTo     | role |
      | firstRANDOM | LastRANDOM | Random_Num | mymailRANDOM@gmail.com | OWRandom_id | Quality    | Senior QA   | Hyderabad | PG            | Male    | B+         | 25-05-2005 | 04-08-2025 | xzjgn@gmail.com | HR   |
      And Click on add button
      Then Employee should be add succesfully.
      And Select the above employee
      Then delete the employee.