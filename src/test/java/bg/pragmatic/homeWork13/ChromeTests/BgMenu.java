package bg.pragmatic.homeWork13.ChromeTests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import bg.pragmatic.util.Browser;

public class BgMenu {

	String bgMenuUrl = "https://bgmenu.com/";

	@Before
	public void setup() {
		Browser.openChrome();
		Browser.driver.get(bgMenuUrl);
	}

	@After
	public void quit() {
		Browser.quit();
	}

	@Test
	public void findBestRestorant() {

		 try {

		WebElement addressInput = Browser.driver
				.findElement(By.cssSelector("input.js-area-select.ac-zone.ui-autocomplete-input"));
		Actions builder = new Actions(Browser.driver);
		builder
			.click(addressInput)
			.sendKeys("Улица Фредерик Жолио Кюри")
			.pause(10020)
			.sendKeys(Keys.DOWN)
			.sendKeys(Keys.ENTER)
			.perform();

		WebElement searchRestButton = Browser.driver.findElement(By.id("search-submit"));
		if (searchRestButton.isEnabled()) {
			searchRestButton.click();
		} else {
			fail("Search button is disabled");
		}
		 } catch (NoSuchElementException e) {
		// TODO Auto-generated catch block
		 e.printStackTrace();
		 fail("Test is failed. No such element exeption is catched ");
		 }
		
		try {
			WebElement searchBar = Browser.driver.findElement(By.id("search"));
			searchBar.clear();
			searchBar.sendKeys("spaghetti kitchen");
			WebElement searchRestorants = Browser.driver.findElement(By.id("search-submit"));
			searchRestorants.click();
			Browser.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebElement spaghettiRestorant = Browser.driver.findElement(By.cssSelector("a > h2"));
			spaghettiRestorant.click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			fail("2nd part failed. No such element exeption is catched ");
		}
		
		Assert.assertTrue("Очакваният ресторант не е намерен.", Browser.driver.getTitle().contains("Spaghetti"));
	}

}
// }
