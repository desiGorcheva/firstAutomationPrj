package bg.pragmatic.homeWork12.FireFoxTests.CarConfig;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import bg.pragmatic.util.Browser;

public class CarColorConfigPositiveTest {

	String configUrl = "http://pragmatic.bg/automation/lecture13/Config.html";

	@Before
	public void setup() {
		Browser.openChrome();
	}

	@After
	public void quit() {
		Browser.quit();
	}

	@Test
	public void selectCarColors() {
		Browser.driver.get(configUrl);

		WebElement redColorMultiSelect = Browser.driver
				.findElement(By.xpath("//option[@value='rd']"));
		WebElement silverColorMultiSelect = Browser.driver
				.findElement(By.xpath("//select[@name='color']//option[@value='sl']"));
		WebElement colorMenu = Browser.driver.findElement(By.xpath("//select[@name='color']"));
		Actions builder = new Actions(Browser.driver);

		builder
			.keyDown(Keys.CONTROL)
			.click(redColorMultiSelect)
			.click(silverColorMultiSelect)
			.keyUp(Keys.CONTROL)
			.perform();

		Select selected = new Select(colorMenu);
		ArrayList<WebElement> selectedColors = (ArrayList<WebElement>) selected.getAllSelectedOptions();
		List<String> actualColors = new ArrayList<>();
		
 		for (WebElement curSelect: selectedColors) {
			actualColors.add(curSelect.getText());
		}
		
		ArrayList<String> expColors = new ArrayList<String>();
		expColors.add("Red");
		expColors.add("Silver");
		//
//		for (WebElement option : selectedColors) {
//			System.out.println(option.getText());
//		}
		
		Assert.assertArrayEquals("Not correct multiselect", expColors.toArray(), actualColors.toArray());
	}
}
