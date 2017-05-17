package utils;

import org.openqa.selenium.WebDriver;

/**
 * Created by ngoyal on 5/13/2017.
 */
public class ThreadLocalDriver {

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getThreadLocalDriver() {
        return threadLocalDriver.get();
    }

    public static void setThreadLocalWebDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }
}
