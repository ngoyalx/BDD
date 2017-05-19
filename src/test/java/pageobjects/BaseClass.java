package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Created by ngoyal on 5/19/2017.
 */
public class BaseClass {

    public static WebDriver driver = null;
    public static AppiumDriver<MobileElement> mobileDriver = null;
    public final static Logger logger = Logger.getLogger("Logger");

    public BaseClass(WebDriver driver){
        this.driver = driver;
    }

    public BaseClass(AppiumDriver<MobileElement> driver){
        this.mobileDriver = driver;
    }

    public BaseClass(){

    }

    /**
     * Will take the sceenshot using the driver instance
     * @return
     */
    public static byte[] takesScreenShot(){
        byte[] screenShot;
        if(driver!=null) {
            screenShot= ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            return screenShot;
        }else if(mobileDriver!=null){
            screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            return screenShot;
        }else{
            return null;
        }

    }

}
