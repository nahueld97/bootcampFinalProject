package excercise1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class GoogleSearchBar {

	private WebDriver driver;
	private Wait<WebDriver> wait;
	final private String url = "https://google.com/";

	final private By searchBarLocator = By.cssSelector("[type=\"search\"]");
	final private By resultsListLocator = By.cssSelector("[role=\"listbox\"]");
	final private By resultOptionLocator = By.cssSelector("[role=\"option\"]");
	final private By imageSuggestionLocator = By.className("sbre");
	final private By linkTextLocator = By.cssSelector("[role=\"text\"]");
	final private By searchResultLocator = By.id("search");

	@Test
	public void test() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//Launch Google Chrome
		WebElement searchBarElement = driver.findElement(searchBarLocator);
		
		//Enter "auto" on the search bar
		searchBarElement.sendKeys("auto");
		
		//wait ajax suggestion box to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultsListLocator));
		
		//get/store all the options of a suggestion box
		WebElement resultsElement = driver.findElements(resultsListLocator).get(0);
		List<WebElement> suggestionsList = resultsElement.findElements(resultOptionLocator);
		
		//print all the suggestions one by one
		List<String> suggestionsAutoStrings = new LinkedList<String>();
		for (WebElement webElement : suggestionsList) {
			System.out.println("-"+webElement.getAttribute("aria-label"));
			suggestionsAutoStrings.add(webElement.getAttribute("aria-label")); //save the labels for future comparison
		}
		
		//clear the input of the searchBar
		searchBarElement.clear();
		
		//Enter "automation" on the search bar
		searchBarElement.sendKeys("automation");
		
		//wait the sugestion box to change or appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultsListLocator));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//get/store all the options of a suggestion box
		resultsElement = driver.findElements(resultsListLocator).get(0);
		suggestionsList = resultsElement.findElements(resultOptionLocator);
		
		//print all the suggestions one by one
		List<String> suggestionsAutomationStrings = new LinkedList<String>();
		for (WebElement webElement : suggestionsList) {
			System.out.println("-"+webElement.getAttribute("aria-label"));
			suggestionsAutomationStrings.add(webElement.getAttribute("aria-label"));//save the labels for future comparison
		}
		
		//check no coincidences between both list of suggestions
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
		
		//click on first suggestion with image
		resultsElement.findElements(imageSuggestionLocator).get(0).click();

		//check that the first obtained result is a page from "store.steampowered.com"
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultLocator));
		WebElement pageResult = driver.findElements(linkTextLocator).get(0);

		Assert.assertTrue(pageResult.getText().contains("store.steampowered.com"),"La pagina no es \"store.steampowered.com\"");
	}

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
}
