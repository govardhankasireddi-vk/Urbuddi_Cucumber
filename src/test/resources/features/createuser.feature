Feature: Register Employee
  This feature is to create an employee in urbuddi portal using excel.
@skip
  Scenario Outline: Create a new user using Excel data
    Given Launch the browser
    When User loads data from excel "<ExcelPath>" and sheet "<SheetName>"
    And add employees in the portal
    Examples:
      | ExcelPath                                 | SheetName |
      | src/test/java/resources/Urbuddi_data.xlsx | Sheet1    |


