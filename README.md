Deliverable3 Web Testing Yue Li (yul134)

In this repository, it contains a feature folder which have all the .feature file, and one stepdefinition file and one test runner file. Since it is a e-commercial website, I devide the user story into shopping cart, login and search items. I try to test different cases just as I am shopping online in real life.
I have several issues solved with the help of Prof Iser, which is the class configuration, and test failure, which is caused by the organization of the feature file and the step defination file. However, so far, I still have one problem not solved that the driver's issue.

Feature 1: Log in

 User Story:
  As a user, I always need to login to this website
  If I have typed right username and password, I should be able to login
  Also there are some cases that I might fail to login
 
     Scenario: login success
     Scenario: login fail with wrong username or password
     Scenario: login fail with empty username

Feature 2: Shopping cart

 User Story:
   As a e-commercial website, shopping cart is quite a vital feature
   As a user, I will add my preferred product to the shopping cart,
   and I will update the shopping cart such as remove items
   or update the quantity of the item

    Scenario: add to shopping cart
    Scenario: update the quantity of the product in the shopping cart
    Scenario: remove item from the shopping cart


Feature 3: Search items

 User Story:
  As a user, i would like to search for items
  the website should be able to return right information
    
    Scenario: Search accurately
    Scenario: Search roughly
    Scenario: Search other things
