package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.net.URL;

/**
 * Created by ngoyal on 5/11/2017.
 */
public class DriverFactory {

    public static WebDriver driver = null;
    public static AppiumDriver<MobileElement> driver1 = null;
    private static String isRemoteExecution="";
    private static String remoteURL="";
    private static File app;


    public static WebDriver getWebDriver(String browser){
        try {
            isRemoteExecution = GlobalProperties.getPropertyMap().get("isRemoteExecution");
            if (isRemoteExecution.equalsIgnoreCase("true")) {
                remoteURL = GlobalProperties.getPropertyMap().get("remoteGridURL");
                remoteURL = remoteURL + "/wd/hub";
                URL url = new URL(remoteURL);
                DesiredCapabilities capabilities = new DesiredCapabilities();
                driver = new RemoteWebDriver(url,capabilities);
                return driver;
            }else{
                if(browser.toLowerCase().contains("firefox")){
                    driver = configureDriver("firefox");
                }else if(browser.toLowerCase().contains("chrome")){
                    driver = configureDriver("chrome");
                }else {
                    driver = null;
                }
                return driver;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static AppiumDriver<MobileElement> getMobileDriver(String browser){
        try {
            if (isRemoteExecution.equalsIgnoreCase("true")) {
                remoteURL = GlobalProperties.getPropertyMap().get("remoteGridURL");
            } else {
                remoteURL = GlobalProperties.getPropertyMap().get("localAppiumURL");
            }
            remoteURL = remoteURL + "/wd/hub";
            URL url = new URL(remoteURL);

            DesiredCapabilities capabilities = new DesiredCapabilities();

            if (browser.toLowerCase().contains("androidnative")) {
                driver =  new AndroidDriver(url, getAndroidNativeCapabilities(capabilities));
            } else if (browser.toLowerCase().contains("iosnative")) {
                driver = new IOSDriver(url, getIOSNativeCapabilities(capabilities));
            } else if (browser.toLowerCase().contains("androidweb")) {
                driver = new AndroidDriver<MobileElement>(url, getAndroidWebCapabilities(capabilities));
            } else if (browser.toLowerCase().contains("iosweb")) {
                driver = new IOSDriver<MobileElement>(url, getIOSWebCapabilities(capabilities));
            } else {
                return null;
            }
            return driver1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    private static WebDriver configureDriver(String browser){
        try{
            String current_OS = GlobalProperties.getPropertyMap().get("os.name").toLowerCase();
            if(browser.equalsIgnoreCase("chrome")){
                if(current_OS.contains("mac")){
                    LogUtility.log.info("Requested browser is Chrome-----Current operating system is Mac");
                    System.setProperty("webdriver.chrome.driver",GlobalProperties.ChromeDriverPathForMac);
                    LogUtility.log.info("xxxxxxxxx---Setting up environment for Mac Chrome driver");
                    driver = new ChromeDriver();
                    LogUtility.log.info("Opening up new chrome browser for Mac");
                }else if(current_OS.contains("windows")){
                    LogUtility.log.info("Requested browser is Chrome-----Current operating system is Windows");
                    System.setProperty("webdriver.chrome.driver",GlobalProperties.ChromeDriverPathForWindows);
                    LogUtility.log.info("xxxxxxxxx---Setting up environment for Windows Chrome driver");
                    driver = new ChromeDriver();
                    LogUtility.log.info("Opening up new chrome browser for Windows");
                }else{
                    LogUtility.log.info("Operating system not identified");
                    return null;
                }
                driver.manage().window().maximize();
                return driver;
            }else if(browser.equalsIgnoreCase("firefox")) {
                if (current_OS.contains("mac")) {
                    LogUtility.log.info("Requested browser is Firefox-----Current operating system is MAC");
                    System.setProperty("webdriver.gecko.driver", GlobalProperties.FirefoxDriverPathForMac);
                    LogUtility.log.info("Setting up firefox driver for MAC");
                    driver = new FirefoxDriver();
                    LogUtility.log.info("Opening up firefox browser for MAC");
                } else if (current_OS.contains("windows")) {
                    LogUtility.log.info("Requested browser is Firefox-----Current operating system is Windows");
                    System.setProperty("webdriver.gecko.driver", GlobalProperties.FirefoxDriverPathForWindows);
                    LogUtility.log.info("Setting up firefox driver for Windows");
                    driver = new FirefoxDriver();
                    LogUtility.log.info("Opening up firefox driver for Windows");
                } else {
                    System.out.println("Unidentified OS");
                    return null;
                }
                driver.manage().window().maximize();
                return driver;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }



    private static DesiredCapabilities getAndroidNativeCapabilities(DesiredCapabilities capabilities){

        if(isRemoteExecution.equalsIgnoreCase("true")){
                app = new File(GlobalProperties.remoteAppDir,GlobalProperties.getPropertyMap().get("androidAppName"));
        }else{
                app = new File(GlobalProperties.localAppDir,GlobalProperties.getPropertyMap().get("androidAppName"));
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("androidPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,GlobalProperties.getPropertyMap().get("androidDeviceName"));
        return capabilities;
    }


    private static DesiredCapabilities getAndroidWebCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("androidPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,GlobalProperties.getPropertyMap().get("androidDeviceName"));
        return capabilities;
    }

    private static DesiredCapabilities getIOSNativeCapabilities(DesiredCapabilities capabilities){
        if(isRemoteExecution.equalsIgnoreCase("true")){
                app = new File(GlobalProperties.remoteAppDir,GlobalProperties.getPropertyMap().get("iosAppName"));
        }else{
                app = new File(GlobalProperties.localAppDir,GlobalProperties.getPropertyMap().get("iosAppName"));
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("iosPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM, "MAC");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,GlobalProperties.getPropertyMap().get("iosPlatformVersion"));
        return capabilities;
    }

    private static DesiredCapabilities getIOSWebCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("iosPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM, "MAC");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,GlobalProperties.getPropertyMap().get("iosPlatformVersion"));
        return capabilities;
    }

    public static byte[] takesScreenShot(){
        byte[] screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    public static void tearDown(){
        if(driver!=null){
            driver.quit();
        }if(driver1!=null){
            driver1.quit();
        }

    }
}
