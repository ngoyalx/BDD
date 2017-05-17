package stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.PropertyConfigurator;
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


    public GlobalProperties globalProperties = null;
    private PropertiesFileReader propertiesReader= null;
    public static RequestSpecification requestSpec = null;

    public Hooks(){
        globalProperties = new GlobalProperties();
        setAllPropertiesInGlobalHashMap();
        PropertyConfigurator.configure(GlobalProperties.LogFilePath);
    }

    @Before
    public void setupExecutionEnvironment(){
        if(GlobalProperties.getPropertyMap().get("AUT").toLowerCase().contains("web")){
            setupWebEnvironment();
        }else if(GlobalProperties.getPropertyMap().get("AUT").toLowerCase().contains("mobile")){
            setupMobileEnvironment();
        }else if(GlobalProperties.getPropertyMap().get("AUT").toLowerCase().contains("api")){
            setupAPIEnvironment();
        }
    }


    @After
    public void tearDown(Scenario scenario){
        takeScreenShotOnFailure(scenario);
        DriverFactory.tearDown();
    }





    private void setAllPropertiesInGlobalHashMap() {
        if (propertiesReader == null) {
            propertiesReader = new PropertiesFileReader();
        }
        propertiesReader.loadHashMapWithConfigFile(GlobalProperties.getPropertyMap());
        propertiesReader.reloadMapWithSystemProperties(GlobalProperties.getPropertyMap());
        LogUtility.log.info("fetched all properties");
    }


    private void setupWebEnvironment(){
            if(GlobalProperties.getPropertyMap().get("browser").toLowerCase().contains("firefox")){
                DriverFactory.getWebDriver(GlobalProperties.getPropertyMap().get("browser"));
            }else if(GlobalProperties.getPropertyMap().get("browser").toLowerCase().contains("chrome")){
                DriverFactory.getWebDriver(GlobalProperties.getPropertyMap().get("browser"));
            }
    }

    private void setupMobileEnvironment(){
            if(GlobalProperties.getPropertyMap().get("browser").toLowerCase().contains("androidnative")){
                DriverFactory.getMobileDriver(GlobalProperties.getPropertyMap().get("browser"));
            }else if(GlobalProperties.getPropertyMap().get("browser").toLowerCase().contains("iosnative")){
                DriverFactory.getMobileDriver(GlobalProperties.getPropertyMap().get("browser"));
            }
    }

    private void setupAPIEnvironment(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Authorization",GlobalProperties.getPropertyMap().get("Authorization"));
        builder.addHeader("Accept", "application/json");
        requestSpec = builder.build();
        requestSpec.baseUri(GlobalProperties.getPropertyMap().get("ApiEndPoint"));
    }

    private void takeScreenShotOnFailure(Scenario scenario){
        if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("web")){
            if(scenario.isFailed()) {
                byte[] screenShot = DriverFactory.takesScreenShot();
                scenario.embed(screenShot,"image/png");
            }
        }

    }




}
