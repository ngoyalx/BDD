package stepdefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import utils.GlobalProperties;

/**
 * Created by ngoyal on 5/17/2017.
 */


@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/main/java/featurefiles",
        glue="stepdefinitions",
        plugin={"pretty", "json:target/cucumberreports.json"}
)
public class RunTest {
}
