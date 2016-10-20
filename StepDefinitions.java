/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineStore;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private final String HOME_PAGE = "http://store.demoqa.com/";

    /*
     * homepage feature
     */

    @Given("a Firefox browser")
    public void openFirefox() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
    }
    
    @When("I navigate to the home page")
    public void navigateHome() {
        driver.get(HOME_PAGE);
    }
    
    @Then("the title should be (.*)")
    public void checkPageTitle(String title) {
        assertEquals(title, driver.getTitle());
    }

    @Given("a the product page")
    public void openproduct(){
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
        driver.get("http://store.demoqa.com/products-page/product-category/");
    }
    @When("I click the logo picture at the head of the page")
    public void clickLogo(){
        WebElement logo = driver.findElement(By.id("logo"));
        logo.click();
    }
    @Then("I should be back to the home page")
    public void checkLogo(){
        wait = new WebDriverWait(driver, 15);
        assertEquals(HOME_PAGE, driver.getTitle());
    }


    /*
     * login feature
     */
    @Given("^a login page$")
    public void LoginPage(){
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
        driver.get("http://store.demoqa.com/products-page/your-account/");
    }

    @When("I login with both username and password correct")
    public void loginSuccess(){
        WebElement username = driver.findElement(By.id("log"));
        WebElement password = driver.findElement(By.id("pwd"));
        username.sendKeys("yul134");
        password.sendKeys("M*tGNA5NtUiD^o3v");
        WebElement login = driver.findElement(By.id("login"));
        login.click();
    }
    @Then("I should be able to logout")
    public void logout(){
            wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("(Logout)")));
            driver.findElement(By.linkText("(Logout)"));
        }


    @When("I login with wrong username or password")
    public void loginFail1() {
        WebElement username = driver.findElement(By.id("log"));
        WebElement password = driver.findElement(By.id("pwd"));
        username.sendKeys("yul134");
        password.sendKeys("12345");
        WebElement login = driver.findElement(By.id("login"));
        login.click();
    }
    @Then("I should see error message")
    public void errorMessage1(){
        wait = new WebDriverWait(driver, 15);
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.response")));
        assertEquals("ERROR: Invalid login credentials.", message.getText());
    }

    @When("I login with empty username")
    public void loginFail2(){
        WebElement username = driver.findElement(By.id("log"));
        username.clear();
        WebElement login = driver.findElement(By.id("login"));
        login.submit();
    }
    @Then("I should see error message")
    public void errorMessage2(){
        wait = new WebDriverWait(driver, 15);
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.response")));
        assertEquals("Please enter your username and password.", message.getText());
    }

    /*
     * shopping cart feature
     */

    @Given("that I am on the product category page")
    public void openProduct(){
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
        driver.get("http://store.demoqa.com/products-page/product-category/");
    }

    @When("I choose to buy an iPhone5")
    public void buyProduct(){
        WebElement buy = driver.findElement(By.name("Buy"));
        buy.submit();
        wait = new WebDriverWait(driver, 15);

    }
    @Then("I should see a successful message pop out")
    public void checkAdd(){
        wait = new WebDriverWait(driver, 15);
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.response")));
        assertEquals("You just added \"iPhone 5\" to your cart.", message.getText());
    }

    @When("After I add an iPhone5 to shopping cart, I click check out")
    public void checkout(){
        WebElement buy = driver.findElement(By.name("Buy"));
        buy.submit();
        wait = new WebDriverWait(driver, 15);
        WebElement goToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Go to Checkout")));
        goToCart.click();
    }
    @Then("I should see the iPhone is already in the shopping cart")
    public void checkcart(){
        wait = new WebDriverWait(driver, 15);
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slide1")));
        String str = cart.getText();
        assertTrue(str.contains("iPhone 5"));

    }
    @Given("I am on the shopping cart page with one iPhone5 in it")
    public void openCart1(){
            System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
            driver = new FirefoxDriver();
            wait = new WebDriverWait(driver, 15);
            driver.get("http://store.demoqa.com/products-page/product-category/n/");
            WebElement buy = driver.findElement(By.name("Buy"));
            buy.submit();
            WebElement goToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Go to Checkout")));
            goToCart.click();
    }
    @When("I would like to buy 2 iPhone5")
    public void updateCart(){
        wait = new WebDriverWait(driver, 15);
        WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantity")));
        quantity.clear();
        quantity.sendKeys("2");
        WebElement update = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("submit")));
        update.click();
    }
    @Then("the shopping cart should be updated")
    public void checkUpdate(){
        wait = new WebDriverWait(driver, 15);
        WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantity")));
        assertEquals("2",quantity.getAttribute("value"));
        WebElement total = driver.findElement(By.cssSelector("td.wpsc_product_price.wpsc_product_price_0"));
        assertEquals("$24.00",total.getText());
    }

    @Given("I am on the shopping cart page with one iPhone5 in it")
    public void openCart2(){
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        //System.setProperty("webdriver.gecko.driver", "libs\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 15);
        driver.get("http://store.demoqa.com/products-page/product-category/n/");
        WebElement buy = driver.findElement(By.name("Buy"));
        buy.submit();
        WebElement goToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Go to Checkout")));
        goToCart.click();
    }
    @When("I click remove button")
    public void removeItem(){
        wait = new WebDriverWait(driver, 15);
        WebElement remove = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]")));
        remove.click();
    }
    @Then("the item should be removed from the shopping cart")
    public void checkRemovingItem() {
        wait = new WebDriverWait(driver, 15);
        if (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]")))) {
            WebElement cart = driver.findElement(By.cssSelector("div.entry-content"));
            String str = cart.getText();
            assertFalse(str.contains("iPhone 5"));
        }
    }


    @After
    public void cleanUp() {
        driver.quit();
    }


}
