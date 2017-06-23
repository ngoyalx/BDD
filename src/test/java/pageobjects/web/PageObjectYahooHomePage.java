package pageobjects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.BaseClass;
import utils.DriverFactory;

/**
 * Created by ngoyal on 5/13/2017.
 */
public class PageObjectYahooHomePage extends BaseClass {

	public PageObjectYahooHomePage() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "uh-search-box")
	static WebElement searchBox;

	@FindBy(id = "uh-search-button")
	static WebElement searchButton;

	public void openHomePage() {
		try {
			driver.get("https://www.yahoo.com");
			BaseClass.logger.info("Driver has been launched");
			BaseClass.logger.error("Ttest error");
			BaseClass.logger.debug("test debug");
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void search() throws Exception {

		Thread.sleep(4000);
		searchBox.sendKeys("test");
		searchButton.click();
		System.out.println("element clicked");
		Thread.sleep(2000);
	}
}
