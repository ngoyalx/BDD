package stepdefinitions.mobile;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import pageobjects.BaseClass;
import utils.DriverFactory;
import utils.GlobalProperties;
import utils.LogUtility;
import utils.PropertiesFileReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Created by ngoyal on 5/15/2017.
 */
public class Hooks {

    public WebDriver driver = null;
    public AppiumDriver<MobileElement> driver1 = null;
    BaseClass baseClass = null;



    public GlobalProperties globalProperties = null;
    private PropertiesFileReader propertiesReader= null;
    public static RequestSpecification requestSpec = null;

    public Hooks(){
        globalProperties = new GlobalProperties();
        setAllPropertiesInGlobalHashMap();
       // PropertyConfigurator.configure(GlobalProperties.LogFilePath);
    }


    /**
     * Will setup the execution environment before each scenario for web, mobile and API
     */
    @Before
    public void setupExecutionEnvironment(){
        if(GlobalProperties.getPropertyMap().get("AUT").toLowerCase().contains("web")){
            setupWebEnvironment();
            baseClass = new BaseClass(driver);
        }else if(GlobalProperties.getPropertyMap().get("AUT").toLowerCase().contains("mobile")){
            setupMobileEnvironment();
            baseClass = new BaseClass(driver1);
        }else if(GlobalProperties.getPropertyMap().get("AUT").toLowerCase().contains("api")){
            setupAPIEnvironment();
            baseClass = new BaseClass();
        }
    }

    private void initializeBaseClass(){
        if(driver!=null){

        }
    }


    /**
     * Will take the screenshot for failed scenario and will kill the driver instance.
     * @param scenario
     */
    @After
    public void tearDown(Scenario scenario){
        takeScreenShotOnFailure(scenario);
        DriverFactory.tearDown();
    }


    /**
     * Will load the config.properties and System properties in a global hashmap
     */
    private void setAllPropertiesInGlobalHashMap() {
        if (propertiesReader == null) {
            propertiesReader = new PropertiesFileReader();
        }
        propertiesReader.loadHashMapWithConfigFile(GlobalProperties.getPropertyMap());
        propertiesReader.reloadMapWithSystemProperties(GlobalProperties.getPropertyMap());
        LogUtility.log.info("fetched all properties");
    }


    /**
     * Will setup the environment for web execution
     */
    private void setupWebEnvironment(){
            if(GlobalProperties.getPropertyMap().get("browser").toLowerCase().contains("firefox")){
                this.driver = DriverFactory.getWebDriver(GlobalProperties.getPropertyMap().get("browser"));
            }else if(GlobalProperties.getPropertyMap().get("browser").toLowerCase().contains("chrome")){
                this.driver = DriverFactory.getWebDriver(GlobalProperties.getPropertyMap().get("browser"));
            }
    }

    /**
     * Will setup the environment for mobile execution
     */
    private void setupMobileEnvironment(){
            if(GlobalProperties.getPropertyMap().get("browser").toLowerCase().contains("androidnative")){
                this.driver1 = DriverFactory.getMobileDriver(GlobalProperties.getPropertyMap().get("browser"));
            }else if(GlobalProperties.getPropertyMap().get("browser").toLowerCase().contains("iosnative")){
                this.driver1 = DriverFactory.getMobileDriver(GlobalProperties.getPropertyMap().get("browser"));
            }
    }

    /**
     * Will setup the execution environment for API
     */
    private void setupAPIEnvironment(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Authorization",GlobalProperties.getPropertyMap().get("Authorization"));
        builder.addHeader("Accept", "application/json");
        requestSpec = builder.build();
        requestSpec.baseUri(GlobalProperties.getPropertyMap().get("ApiEndPoint"));
    }


    /**
     * Will take the sceenshot for failed scenario in case of web execution
     * @param scenario
     */
    private void takeScreenShotOnFailure(Scenario scenario){
        if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("web")){
            if(scenario.isFailed()) {
                byte[] screenShot = BaseClass.takesScreenShot();
                scenario.embed(screenShot,"image/png");
            }
        }

    }




}
