package bg.pragmatic.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {

	public static WebDriver driver;
	
	public static void openChrome() {
		System.setProperty("webdriver.chrome.driver" ,"C:\\Desi\\Program Files\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void openFireFox() {
		System.setProperty("webdriver.gecko.driver", "C:\\Desi\\Program Files\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public static void openEdge() {
		System.setProperty("webdriver.edge.driver","C:\\Desi\\Program Files\\Drivers\\MicrosoftWebDriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public static void quit() {
		driver.quit();
	}

	
}
