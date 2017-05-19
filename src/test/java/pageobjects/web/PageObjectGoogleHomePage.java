package pageobjects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.BaseClass;
import utils.DriverFactory;

/**
 * Created by ngoyal on 5/13/2017.
 */
public class PageObjectGoogleHomePage extends BaseClass{


    public PageObjectGoogleHomePage(){

        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//input[@id='gs_htif0']")
    static WebElement searchBox;

    @FindBy(xpath="//input[@name='btnK']")
    static WebElement searchButton;

    public void openGoogleHomePage(){
        try {
            driver.get("https://www.google.com");
            BaseClass.logger.info("Driver has been launched");
            BaseClass.logger.error("Ttest error");
            BaseClass.logger.debug("test debug");
            Thread.sleep(5000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void search() throws Exception{
        //searchBox.sendKeys("test");
        searchButton.click();
        System.out.println("element clicked");
        Thread.sleep(2000);
    }
}
