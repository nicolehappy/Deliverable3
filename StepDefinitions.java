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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StepDefinitions {

    public WebDriver driver;
    public WebDriverWait wait;


    private final String HOME_PAGE = "http://store.demoqa.com/";

    /*
     * homepage feature
     * As a user, I am very first to this website
     * I will check the title of the website
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


    @When("I click the logo picture")
    public void clickLogo(){
        WebElement logo = driver.findElement(By.id("logo"));
        logo.click();
    }
    @Then("I should be stay in home page")
    public void checkLogo(){
        wait = new WebDriverWait(driver, 15);
        assertEquals(HOME_PAGE, driver.getTitle());
    }






    /*
     * login feature
     * As a user, I always need to login to this website
     * If I have typed right username and password, i should be able to login
     * Also there are some cases that I might fail to login
     */

    @Given("a login page")
    public void LoginPage() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        //driver = new FirefoxDriver();
        //wait = new WebDriverWait(driver, 30);
        driver.get("http://store.demoqa.com/products-page/your-account/");
    }


    @When("I login with both username and password correct")
    public void loginSuccess() {
        WebElement username = driver.findElement(By.id("log"));
        WebElement password = driver.findElement(By.id("pwd"));
        username.sendKeys("yul134");
        password.sendKeys("M*tGNA5NtUiD^o3v");
        WebElement login = driver.findElement(By.id("login"));
        login.click();
    }

    @Then("I should be able to logout")
    public void logout() {
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

    @Then("I should see an error message")
    public void errorMessage1() {
        wait = new WebDriverWait(driver, 15);
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.response")));
        assertEquals("ERROR: Invalid login credentials.", message.getText());
    }


    @When("I login with empty username")
    public void loginFail2() {
        WebElement username = driver.findElement(By.id("log"));
        username.clear();
        WebElement login = driver.findElement(By.id("login"));
        login.submit();
    }

    @Then("I should see a notice message")
    public void errorMessage2() {
        wait = new WebDriverWait(driver, 15);
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.response")));
        assertEquals("Please enter your username and password.", message.getText());
    }

    /*
     * shopping cart feature
     * As a e-commercial website, shopping cart is quite a vital feature
     * As a user, I will add my preferred product to the shopping cart,
     * and I will update the shopping cart such as remove items
     * or update the quantity of the item
     */

    @Given("the product category page")
    public void openProduct() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver.get("http://store.demoqa.com/products-page/product-category/");
    }

    @When("I choose to buy an iPhone5 and click check out")
    public void buyProduct() {
        WebElement buy = driver.findElement(By.name("Buy"));
        buy.submit();
        wait = new WebDriverWait(driver, 15);
        WebElement goToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Go to Checkout")));
        goToCart.click();

    }

    @Then("I should have the right information in the shopping cart")
    public void checkAdd() {
        wait = new WebDriverWait(driver, 15);
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slide1")));
        String str = cart.getText();
        assertTrue(str.contains("iPhone 5"));
        WebElement subtotal = driver.findElement(By.cssSelector("span.pricedisplay"));
        assertEquals("$12.00", subtotal.getText());
    }

    @Given("the shopping cart page with one iPhone5 in it")
    public void openCart1() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 15);
        driver.get("http://store.demoqa.com/products-page/product-category/n/");
        WebElement buy = driver.findElement(By.name("Buy"));
        buy.submit();
        WebElement goToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Go to Checkout")));
        goToCart.click();
    }

    @When("I change the quantity to 2 and click update")
    public void updateCart() {
        wait = new WebDriverWait(driver, 15);
        WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantity")));
        quantity.clear();
        quantity.sendKeys("2");
        WebElement update = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("submit")));
        update.click();
    }

    @Then("the shopping cart should be updated")
    public void checkUpdate() {
        wait = new WebDriverWait(driver, 15);
        WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantity")));
        assertEquals("2", quantity.getAttribute("value"));

    }

    @When("I click remove button")
    public void removeItem() {
        wait = new WebDriverWait(driver, 15);
        WebElement remove = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]")));
        remove.click();
    }

    @Then("the iPhone should be removed from the shopping cart")
    public void checkRemovingItem() {
        wait = new WebDriverWait(driver, 15);
        if (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]")))) {
            WebElement cart = driver.findElement(By.cssSelector("div.entry-content"));
            String str = cart.getText();
            assertFalse(str.contains("iPhone 5"));
        }
    }

/*
 * search feature
 * as a user, i would like to search for items
 * the website should be able to return right information
 *
 */

    @Given("a search box")
    public void findSearchBox() {
        System.setProperty("webdriver.gecko.driver", "libs/geckodriver");
        //System.setProperty("webdriver.gecko.driver", "libs\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
        driver.get(HOME_PAGE);
    }
    @When("I type iPhone 5")
    public void searchMagicMouse() {
        WebElement searchbox = driver.findElement(By.name("s"));
        searchbox.clear();
        searchbox.sendKeys("iPhone 5");
        searchbox.sendKeys(Keys.RETURN);
    }

    @Then("I should see the iPhone 5 listed in the result")
    public void checkResult() {
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.prodtitle")));
        WebElement result = driver.findElement(By.cssSelector("div.product_grid_display.group"));
        assertTrue(result.getText().contains("iPhone"));
    }

    @When("I type iphone")
    public void searchiphone() {
        WebElement searchbox = driver.findElement(By.name("s"));
        searchbox.clear();
        searchbox.sendKeys("iphone");
        searchbox.sendKeys(Keys.RETURN);
    }

    @When("I type keyboard")
    public void searchkeyboard() {
        WebElement searchbox = driver.findElement(By.name("s"));
        searchbox.clear();
        searchbox.sendKeys("Keyboard");
        searchbox.sendKeys(Keys.RETURN);
    }

    @Then("It should be nothing matched")
    public void searchMessage() throws InterruptedException {
        wait = new WebDriverWait(driver, 15);
        Thread.sleep(5000);
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));
        assertEquals("Sorry, but nothing matched your search criteria. Please try again with some different keywords.",result.getText());
    }

        @After
        public void cleanUp() {
            driver.quit();
        }
    }

