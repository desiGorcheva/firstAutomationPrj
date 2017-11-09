package bg.pragmatic.homeWork12.ChromeTests.BackEnd;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;



public class LoginTest {

	WebDriver driver;
	String backEndUrl = "http://shop.pragmatic.bg/admin/";
//	String forntEndUrl ="http://shop.pragmatic.bg/";
	String backUsername = "admin";
	String backPass = "parola123!";
	String dashboardText;
	WebElement dashboard;
	String backUsernameFail = "test";
	String backPassFail = "test123!";
	WebElement errorMsg;
	
	@Before
	public void setUp() {
		//System.setProperty("webdriver.gecko.driver", "C:\\Desi\\Program Files\\Drivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver" ,"C:\\Desi\\Program Files\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test 
	// Позитивен тест за логин в back-end
	public void LoginPositive() {
		driver.get(backEndUrl);
		//Локираме елементите на логин страницата
		WebElement username = driver.findElement(By.id("input-username"));
		WebElement password = driver.findElement(By.id("input-password"));
		WebElement loginButton = driver.findElement(By.xpath("//div[@class='text-right']//button[@type='submit']"));
		
		username.sendKeys(backUsername);
		password.sendKeys(backPass);
		loginButton.click();
		
		 try {
		dashboard = driver.findElement(By.xpath("//h1[text() = 'Dashboard']"));
		dashboardText = dashboard.getText();

		    } catch (NoSuchElementException e) {
		    
		    }
		    
		 assertEquals("Dashboard",dashboardText);
		    
	}
	
	@Test 
	// Негативен тест за логин в back-end
	public void LoginNegative() {
		driver.get(backEndUrl);
		//Локираме елементите на логин страницата
		WebElement username = driver.findElement(By.id("input-username"));
		WebElement password = driver.findElement(By.id("input-password"));
		WebElement loginButton = driver.findElement(By.xpath("//div[@class='text-right']//button[@type='submit']"));
		
		username.sendKeys(backUsernameFail);
		password.sendKeys(backPassFail);
		loginButton.click();
		
		errorMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
		String actualErrorMsg = errorMsg.getText();
		String[] result =  actualErrorMsg.split("\n", 2);
		String expectedErrorMsg= "No match for Username and/or Password.";

		assertEquals(result[0],expectedErrorMsg);
		//errorMsg.getText();
		//System.out.println(errorMsg);
		// assertTrue(errorMsg.getText().contains("No match for Username"));
	}
}
