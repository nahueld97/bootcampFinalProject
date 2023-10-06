package pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DemoBlazeCartPage {

	private WebDriver driver;
	private Wait<WebDriver> wait;
	
	private By homeLinkLocator = By.id("nava");
	private By purchaseOrderLocator = By.cssSelector("[data-target=\"#orderModal\"]");
	private By formLocator = By.id("orderModalLabel");
	private By nameFormLocator = By.id("name");
	private By countryFormLocator = By.id("country");
	private By cityFormLocator = By.id("city");
	private By creditCardFormLocator = By.id("card");
	private By monthFormLocator = By.id("month");
	private By yearFormLocator = By.id("year");
	private By purchaseBtnLocator = By.cssSelector("[onclick=\"purchaseOrder()\"]");
	private By successfullMessageLocator = By.className("text-muted");
	
	private String formName,formCreditCard;
	private String message;
	
	public DemoBlazeCartPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	}

	public void goToHomePage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(homeLinkLocator));
		wait.until(ExpectedConditions.elementToBeClickable(homeLinkLocator));
		driver.findElement(homeLinkLocator).click();
	}
	
	public void clickOnPlaceOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(purchaseOrderLocator));
		driver.findElement(purchaseOrderLocator).click();
	}
	
	public void fillOrderForm(String name, String country, String city, String creditCard, String month, String year) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(formLocator));
		Assert.assertTrue((name != "")&&(creditCard != "")&&(name != null)&&(creditCard != null), "Name and Credit Card mush be on the form");
		formName = name;
		formCreditCard = creditCard;
		
		driver.findElement(nameFormLocator).sendKeys(formName);
		driver.findElement(countryFormLocator).sendKeys((country != null)? country : "");
		driver.findElement(cityFormLocator).sendKeys((city != null)? city : "");
		driver.findElement(creditCardFormLocator).sendKeys(formCreditCard);
		driver.findElement(monthFormLocator).sendKeys((month != null)? month : "");
		driver.findElement(yearFormLocator).sendKeys((year != null)? year : "");
	}
	
	public void clickOnPurchase() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalp")));
		wait.until(ExpectedConditions.elementToBeClickable(purchaseBtnLocator));
		driver.findElement(purchaseBtnLocator).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(successfullMessageLocator));
		wait.until(ExpectedConditions.elementToBeClickable(By.className("confirm")));
		
		message = driver.findElement(successfullMessageLocator).getText();
		
	}
	
	public void checkMessageInformation() {
		Assert.assertTrue(message.contains("Card Number: "+formCreditCard));
		Assert.assertTrue(message.contains("Name: "+formName));
	}
}
