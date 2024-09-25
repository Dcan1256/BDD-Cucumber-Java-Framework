Feature: Login Data Driven with Excel

  Scenario Outline: Login Data Driven Excel
    Given The user is on SauceLabs login page
    Then the user should be redirected to the main page by passing username and password with excel row "<row_index>"


    Examples:
    |row_index|
    |    1    |
    |    3    |
    |    4    |
    |    5    |

