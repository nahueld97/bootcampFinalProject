package excercise1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class GoogleSearchBar {

	private WebDriver driver;
	private Wait<WebDriver> wait;
	final private String url = "https://google.com/";

	final private By searchBar = By.cssSelector("[type=\"search\"]");
	final private By resultsList = By.cssSelector("[role=\"listbox\"]");

	@Test
	public void f() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		WebElement searchBarElement = driver.findElement(searchBar);
		searchBarElement.sendKeys("auto");
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultsList));
		WebElement resultsElement = driver.findElements(resultsList).get(0);
		List<WebElement> suggestionsAuto = resultsElement.findElements(By.cssSelector("[role=\"option\"]"));
		System.out.println("primera busqueda");
		for (WebElement webElement : suggestionsAuto) {
			System.out.println("-"+webElement.getAttribute("aria-label"));
		}
		
		searchBarElement.clear();
		
		searchBarElement.sendKeys("automation");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultsList));
		resultsElement = driver.findElements(resultsList).get(0);
		System.out.println("segunda busqueda");
		List<WebElement> suggestionsAutomation = resultsElement.findElements(By.cssSelector("[role=\"option\"]"));
		for (WebElement webElement : suggestionsAutomation) {
			System.out.println("-"+webElement.getAttribute("aria-label"));
		}
		
		Boolean coincident = true;
		
		for (int i=0; i<suggestionsAuto.size() && coincident ; i++) {
			String textAuto = suggestionsAuto.get(i).getAttribute("aria-label");
			String textAutomation = suggestionsAutomation.get(i).getAttribute("aria-label");
			coincident = (textAuto == textAutomation);
		}
		
		Assert.assertFalse(coincident, "The results list are coincidents");
		
		resultsElement.findElements(By.className("sbct sbre")).get(0).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div/div[12]/div[1]/div[2]/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div/div[1]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div/div/span/a/div/div/div/cite")));
		WebElement pageResult = driver.findElement(By.xpath("/html/body/div[5]/div/div[12]/div[1]/div[2]/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div/div[1]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div/div/span/a/div/div/div/cite"));

		Assert.assertTrue(pageResult.getText().contains("store.steampowered.com"),"La pagina no es \"store.steampowered.com\"");
	}

	@BeforeTest
	public void beforeTest() {
		driver.get(url);
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterSuite
	public void afterSuite() {
	}

}
