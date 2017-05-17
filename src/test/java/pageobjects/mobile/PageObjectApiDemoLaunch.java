package pageobjects.mobile;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

/**
 * Created by ngoyal on 5/14/2017.
 */
public class PageObjectApiDemoLaunch extends DriverFactory {

    @AndroidFindBy(id = "android:id/text1")
    MobileElement views;

    public PageObjectApiDemoLaunch(){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void launchViews(){
        views.click();
    }

}
