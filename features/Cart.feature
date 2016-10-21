Feature: Shopping cart


  Scenario: add to shopping cart
    Given a Firefox browser
    And the product category page
    When I choose to buy an iPhone5 and click check out
    Then I should have the right information in the shopping cart

  Scenario: update the quantity of the product in the shopping cart
    Given a Firefox browser
    And the shopping cart page with one iPhone5 in it
    When I change the quantity to 2 and click update
    Then the shopping cart should be updated

  Scenario: remove item from the shopping cart
    Given a Firefox browser
    And the shopping cart page with one iPhone5 in it
    When I click remove button
    Then the iPhone should be removed from the shopping cart
