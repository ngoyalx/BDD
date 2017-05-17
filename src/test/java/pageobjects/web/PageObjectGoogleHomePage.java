package pageobjects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

/**
 * Created by ngoyal on 5/13/2017.
 */
public class PageObjectGoogleHomePage extends DriverFactory{


    public PageObjectGoogleHomePage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="gs_htif0")
    static WebElement searchBox;

    @FindBy(name="btnK")
    static WebElement searchButton;

    public void openGoogleHomePage(){
        driver.get("https://www.google.com");
    }

    public void search(){
        searchBox.sendKeys("Selenium");
        searchButton.click();
    }
}
