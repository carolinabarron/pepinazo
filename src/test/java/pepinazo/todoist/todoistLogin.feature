Feature: Todoist Login

  Scenario: User is able to login with email credentials
    Given I navigate to the todoist website
    When I enter correct email credentials
    Then I can see the Project page

  Scenario:  User can login with google credentials
    Given I navigate to the todoist website
    When I enter correct google credentials
    Then I can see the Project page for google login


  Scenario: User can login with facebook credentials
    Given I navigate to the todoist website
    When I enter correct facebook credentials
    Then I can see the Project page for facebook login


  Scenario: User can login with apple ID credentials
    Given I navigate to the todoist website
    When I enter correct Apple ID
    Then I can see the Project page

  Scenario Outline: User is able login with email credentials
    Given I navigate to todoist
    When I enter <user> and <password>
    Then I can see the Project page

    Examples:
      | user                   | password  |
      | jomarnavarro@gmail.com | Test@1234 |
      | jomarnavarro@gmail.com | Test@1234 |
      | jomarnavarro@gmail.com | Test@1235 |


  Scenario: User can login con google credentials
    Given I navigate to todoist
    When I log in using facebook
    Then I can see the Project page

