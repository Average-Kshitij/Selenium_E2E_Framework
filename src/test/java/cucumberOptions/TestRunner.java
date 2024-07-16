package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions (
		features= "src/test/java/features",
		glue="stepDefinations",
		tags ="@search"
		)

public class TestRunner extends AbstractTestNGCucumberTests{
	{
		
	}

}
