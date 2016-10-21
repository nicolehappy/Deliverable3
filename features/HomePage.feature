Feature: Home page
/**
 * User Story:
 * As a user, I am very first to this website 
 * I will check the title of the website
 * I will explore the website a little and can always be back to the home page
 */

	Scenario: Home page title
		Given a Firefox browser
                When I navigate to the home page
                Then the title should be ONLINE STORE | Toolsqa Dummy Test site

