package runnerFile;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",monochrome=true,
glue = "stepDefinitions",
plugin = {"pretty","html:target/HtmlReports"})
public class Runner {

}
