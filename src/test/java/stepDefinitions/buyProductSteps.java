package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pom.DemoBlazeCartPage;
import pom.DemoBlazePage;
import pom.DemoBlazeProductPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class buyProductSteps {
	
	private WebDriver driver;
	private DemoBlazePage homePage;
	private DemoBlazeCartPage cartPage;
	private DemoBlazeProductPage productPage;
	
	@Given("The opened webpage = {string}")
	public void openPageAndSetup(String string) {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(string);
        homePage = new DemoBlazePage(driver);
        productPage = new DemoBlazeProductPage(driver);
        cartPage = new DemoBlazeCartPage(driver);
	}
	
	@When("Add first product to the cart")
	@And ("Add second product to the cart")
	public void addProductToCart() {
		homePage.clickOnPhone();
		homePage.clickFirstProduct();
		productPage.clickOnAddToCart();
	}
	
	@And ("Back to homepage")
	public void goToHomepage() {
		productPage.goToHomePage();
	}
	
	@And ("Go to the cart")
	public void goToCartPage() {
		productPage.goToCart();
	}
	
	@And ("Purchase the selected items")
	public void purchaseSelectedItems() {
		cartPage.clickOnPlaceOrder();
		cartPage.fillOrderForm("x", "x", "x", "123413241234", "01", "40");
		cartPage.clickOnPurchase();
	}
	
	@Then ("A successfull message should be showed")
    @And ("The correct information must be showed")
	public void empty() {}
}
