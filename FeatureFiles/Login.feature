Feature: User Login

  @Sanity
  Scenario: Successful Login.
    Given The user is on SauceLabs login page
    When The user enters valid credentials as username: "standard_user", password: "secret_sauce"
    And The user clicks on the login button
    Then The user should be redirected on the main page
    And The user should see the products page


    @Regression
  Scenario Outline: Login Data Driven
    Given The user is on SauceLabs login page
    When The user enters valid credentials as username: "<username>", password: "<password>"
    And The user clicks on the login button
    Then The user should be redirected on the main page
    And The user should see the products page

    Examples:
    |username|password|
    |    standard_user            | secret_sauce       |
    |    problem_user             | secret_sauce       |
    |    performance_glitch_user  | secret_sauce       |
    |    error_user               | secret_sauce       |
    |    visual_user              | secret_sauce       |