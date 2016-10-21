Deliverable3 Web Testing 

Feature: Log in
/**
 * User Story:
 * As a user, I always need to login to this website
 * If I have typed right username and password, i should be able to login
 * Also there are some cases that I might fail to login
 */

  Scenario: login success
        Given a login page
              When I login with both username and password correct
              Then I should be able to logout

    Scenario: login fail with wrong username or password
        Given a login page
              When I login with wrong username or password
              Then I should see error message

    Scenario: login fail with empty username
        Given a login page
              When I login with empty username
              Then I should see error message


Feature: Shopping cart
/**
 * User Story:
 * As a e-commercial website, shopping cart is quite a vital feature
 * As a user, I will add my preferred product to the shopping cart,
 * and I will update the shopping cart such as remove items
 * or update the quantity of the item
 */

    Scenario: add to shopping cart
        Given a product page
              When I choose to buy an iPhone5
              Then I should see a successful message pop out

    Scenario: check the shopping cart
        Given a product page
              When After I add an iPhone5 to shopping cart, I click check out
              Then I should see the iPhone is already in the shopping cart

    Scenario: update the quantity of the product in the shopping cart
        Given the shopping cart page with one iPhone5 in it
              When I change the quantity to 2 and click update
              Then the shopping cart should be updated

      Scenario: remove item from the shopping cart
        Given the shopping cart page with one iPhone5 in it
              When I click remove button
              Then the item should be removed from the shopping cart


