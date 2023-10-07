package excercise1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

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
	final private By clickableSuggestion = By.cssSelector("[jsaction=\"click:.CLIENT;mouseover:.CLIENT\"]");

	@Test
	public void test() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<String> autoStrings,automationStrings;
		
		List<WebElement> suggestionsList = getResultsFromText("auto");
		autoStrings = printResults(suggestionsList);
		clearSearchBar();
		suggestionsList = getResultsFromText("automation");
		automationStrings = printResults(suggestionsList);
		Boolean coincident = equalLists(autoStrings, automationStrings);
		
		Assert.assertFalse(coincident, "The results list are coincidents");
		
		clickFirstSuggestionWithImage();
		Boolean isFrom = firstResultIsFromPage("store.steampowered.com");
		
		Assert.assertTrue(isFrom,"La pagina no es \"store.steampowered.com\"");
	}

	public List<WebElement> getResultsFromText(String keywords){
		driver.findElement(searchBarLocator).sendKeys(keywords);
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultsListLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(clickableSuggestion));
		WebElement resultsElement = driver.findElements(resultsListLocator).get(0);
		return resultsElement.findElements(resultOptionLocator);
	}
	
	public void clearSearchBar() {
		driver.findElement(searchBarLocator).clear();
	}
	
	public List<String> printResults(List<WebElement> results){
		List<String> suggestionsStrings = new LinkedList<String>();
		for (WebElement webElement : results) {
			System.out.println("-"+webElement.getAttribute("aria-label"));
			suggestionsStrings.add(webElement.getAttribute("aria-label"));
		}
		return suggestionsStrings;
	}
	
	public Boolean equalLists(List<String> l1, List<String> l2){
		Boolean coincident = true;
		String textAuto,textAutomation;
		
		for (int i=0; i<l1.size() && coincident ; i++) {
			textAuto = l1.get(i);
			textAutomation = l2.get(i);
			coincident = (textAuto.equals(textAutomation));
			if(!textAuto.equals(textAutomation)) {
				System.out.println("{"+textAuto+"  "+textAutomation+"}");
			}
		}
		return coincident;
	}
	
	private Boolean firstResultIsFromPage(String pageUrl) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultLocator));
		WebElement pageResult = driver.findElements(linkTextLocator).get(0);
		return pageResult.getText().contains(pageUrl);
	}
	
	private void clickFirstSuggestionWithImage() {
		driver.findElements(resultsListLocator).get(0).findElements(imageSuggestionLocator).get(0).click();
	}

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
