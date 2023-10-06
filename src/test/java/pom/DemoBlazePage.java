package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoBlazePage {

	private WebDriver driver;
	private Wait<WebDriver> wait;
	
	private By phoneCategorieLocator = By.cssSelector("[onclick=\"byCat('phone')\"]");
	private By notebookCategorieLocator = By.cssSelector("[onclick=\"byCat('notebook')\"]");
	private By monitorCategorieLocator = By.cssSelector("[onclick=\"byCat('monitor')\"]");
	private By ProductsLocator = By.id("tbodyid");
	
	public DemoBlazePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	}

	public void clickOnPhone() {
		wait.until(ExpectedConditions.elementToBeClickable(phoneCategorieLocator));
		driver.findElement(phoneCategorieLocator).click();
	}
	
	public void clickOnNotebook() {
		wait.until(ExpectedConditions.elementToBeClickable(notebookCategorieLocator));
		driver.findElement(notebookCategorieLocator).click();
	}
	
	public void clickOnMonitor() {
		wait.until(ExpectedConditions.elementToBeClickable(monitorCategorieLocator));
		driver.findElement(monitorCategorieLocator).click();
	}
	
	public void clickFirstProduct() {
		wait.until(ExpectedConditions.presenceOfElementLocated(ProductsLocator));
		List<WebElement> productsList = driver.findElements(ProductsLocator);
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(ProductsLocator,By.tagName("a")));
		WebElement firstProduct = productsList.get(0).findElement(By.tagName("a"));
		wait.until(ExpectedConditions.visibilityOf(firstProduct));
		wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
		firstProduct.click();
	}
	
	
}
