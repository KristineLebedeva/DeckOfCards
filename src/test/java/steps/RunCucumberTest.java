package steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "not @skip",
        features = "src/test/resources",
        glue = {"steps"},
        plugin = "json:target/jsonReports/cucumber-report.json"
)
public class RunCucumberTest {

}
