
package BDD.testRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/Scenario1.feature",
    glue = "BDD.StepDefinitions",
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true,
    dryRun = false
   
)
public class TestRunner extends AbstractTestNGCucumberTests  {
	   
	
}
