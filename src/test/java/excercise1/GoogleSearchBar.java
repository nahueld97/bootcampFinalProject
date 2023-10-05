package excercise1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.LinkedList;
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
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement searchBarElement = driver.findElement(searchBar);
		searchBarElement.sendKeys("auto");
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultsList));
		WebElement resultsElement = driver.findElements(resultsList).get(0);
		List<WebElement> suggestionsAuto = resultsElement.findElements(By.cssSelector("[role=\"option\"]"));
		List<String> suggestionsAutoStrings = new LinkedList<String>();
		System.out.println("primera busqueda");
		for (WebElement webElement : suggestionsAuto) {
			System.out.println("-"+webElement.getAttribute("aria-label"));
			suggestionsAutoStrings.add(webElement.getAttribute("aria-label"));
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
		List<String> suggestionsAutomationStrings = new LinkedList<String>();
		for (WebElement webElement : suggestionsAutomation) {
			System.out.println("-"+webElement.getAttribute("aria-label"));
			suggestionsAutomationStrings.add(webElement.getAttribute("aria-label"));
		}
		
		Boolean coincident = true;
		
		String textAuto,textAutomation;
		for (int i=0; i<suggestionsAutoStrings.size() && coincident ; i++) {
			textAuto = suggestionsAutoStrings.get(i);
			textAutomation = suggestionsAutomationStrings.get(i);
			coincident = (textAuto.equals(textAutomation));
			if(!textAuto.equals(textAutomation)) {
				System.out.println("{"+textAuto+"  "+textAutomation+"}");
			}
		}
		
		Assert.assertFalse(coincident, "The results list are coincidents");
		
		resultsElement.findElements(By.className("sbre")).get(0).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
		WebElement pageResult = driver.findElements(By.cssSelector("[role=\"text\"]")).get(0);

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
