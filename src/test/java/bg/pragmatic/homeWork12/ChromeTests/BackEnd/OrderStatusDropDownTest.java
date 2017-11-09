package bg.pragmatic.homeWork12.ChromeTests.BackEnd;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.openqa.selenium.support.ui.Select;

public class OrderStatusDropDownTest {

	WebDriver driver;
	String backEndUrl = "http://shop.pragmatic.bg/admin/";
	String backUsername = "admin";
	String backPass = "parola123!";

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Desi\\Program Files\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void driverQuit() {
		driver.quit();
	}

	@Test
	public void checkOrderStatusDropDownMenu() {
		driver.get(backEndUrl);

		WebElement username;
		WebElement password;
		WebElement loginButton;
		try {
			username = driver.findElement(By.id("input-username"));
			password = driver.findElement(By.id("input-password"));
			loginButton = driver.findElement(By.xpath("//div[@class='text-right']//button[@type='submit']"));

			username.sendKeys(backUsername);
			password.sendKeys(backPass);
			loginButton.click();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			fail("Не намерен елемент от логин страницата");
		}

		//try catch Блока не сработва, ако не намери елемента теста не фейлва, ами има error?
		try {
			WebElement salesMenuButton = driver.findElement(By.cssSelector("#menu-sale a.parent"));
			salesMenuButton.click();
			WebElement ordersMenuButton = driver.findElement(By.cssSelector("#collapse26 a"));
			ordersMenuButton.click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			fail("Не намерен елемнт от навигационното меню");
		}
		assertTrue(driver.getTitle().contains("Orders"));

		Select orderStatusDropDown = new Select(driver.findElement(By.cssSelector("#input-order-status")));

		List<String> exp_orderStatusOptions = Arrays.asList(new String[] { "", "Missing Orders", "Canceled",
				"Canceled Reversal", "Chargeback", "Complete", "Denied", "Expired", "Failed", "Pending", "Processed",
				"Processing", "Refunded", "Reversed", "Shipped", "Voided" });
		List<String> ac_orderStatusOptions = new ArrayList<String>();

		for (WebElement option : orderStatusDropDown.getOptions()) {
			ac_orderStatusOptions.add(option.getText());
		}
		Assert.assertArrayEquals(exp_orderStatusOptions.toArray(), ac_orderStatusOptions.toArray());

	}

}
