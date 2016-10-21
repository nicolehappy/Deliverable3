Feature: search items

  Scenario: Search accurately
    Given a search box
    When I type iPhone 5
    Then I should see the iPhone 5 listed in the result



  Scenario: Search roughly
    Given a search box
    When I type iphone
    Then I should see the iPhone 5 listed in the result


  Scenario: Search other things
    Given a search box
    When I type keyboard
    Then It should be nothing matched