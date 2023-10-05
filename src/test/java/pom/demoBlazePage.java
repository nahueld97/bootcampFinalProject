package pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class demoBlazePage {

	private WebDriver driver;
	private Wait<WebDriver> wait;
	
	private By phoneCategorieLocator = By.cssSelector("onclick=\"byCat('phone')\"");
	private By notebookCategorieLocator = By.cssSelector("onclick=\"byCat('notebook')\"");
	private By monitorCategorieLocator = By.cssSelector("onclick=\"byCat('monitor')\"");
	
	public demoBlazePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	}

	public void clickOnPhone() {
		driver.findElement(phoneCategorieLocator).click();
	}
	
	public void clickOnNotebook() {
		driver.findElement(notebookCategorieLocator).click();
	}
	
	public void clickOnMonitor() {
		driver.findElement(monitorCategorieLocator).click();
	}
	
	public void selectFirstProduct() {
		WebElement firstProduct = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a"));
		wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
		firstProduct.click();
	}
	
	
}
