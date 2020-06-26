Feature: Todoist Login
  Scenario Outline: User is able login with email credentials
    Given I navigate to todoist
    When I enter <user> and <password>
    Then I can see the Project page
    Examples:
      | user                   | password  |
      | jomarnavarro@gmail.com | Test@1234 |
      | jomarnavarro@gmail.com | Test@1234 |
      | jomarnavarro@gmail.com | Test@1235 |
