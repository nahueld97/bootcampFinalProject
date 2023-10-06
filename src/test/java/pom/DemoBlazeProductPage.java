package pom;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DemoBlazeProductPage {
	private WebDriver driver;
	private Wait<WebDriver> wait;
	
	private By successBtnLocator = By.className("btn-success");
	private By homeLinkLocator = By.id("nava");
	private By cartLinkLocator = By.id("cartur");
	
	public DemoBlazeProductPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	}
	
	public void clickOnAddToCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(successBtnLocator));
		wait.until(ExpectedConditions.elementToBeClickable(successBtnLocator));
		driver.findElement(successBtnLocator).click();
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goToHomePage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(homeLinkLocator));
		wait.until(ExpectedConditions.elementToBeClickable(homeLinkLocator));
		driver.findElement(homeLinkLocator).click();
	}
	
	public void goToCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartLinkLocator));
		wait.until(ExpectedConditions.elementToBeClickable(cartLinkLocator));
		driver.findElement(cartLinkLocator).click();
	}
}
