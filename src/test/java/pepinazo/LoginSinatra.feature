Feature: Sinatra Login

  Scenario: Login Happy path
    Given I have the correct credentials
    When I navigate to sinatra site
    And I enter the correct credentials
    Then I will be in the Songs Page
    And I will see a welcome message

    Scenario:  Incorrect Login
      Given I have incorrect credentials
      When I navigate to sinatra site
      And I enter the incorrect credentials
      Then I will be in the Login page
      And I will see the Error message for invalid credentials

