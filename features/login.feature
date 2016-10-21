Feature: Log in

  Scenario: login success
    Given a Firefox browser
    And a login page
    When I login with both username and password correct
    Then I should be able to logout

  Scenario: login fail with wrong username or password
    Given a Firefox browser
    And a login page
    When I login with wrong username or password
    Then I should see an error message

    Scenario: login fail with empty username
      Given a Firefox browser
      And a login page
      When I login with empty username
      Then I should see a notice message