package nut.cc.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {},
        glue = "com.shmelev.steps",
        features = {"src/test/resources/features"},
        format = {"pretty", "html:target/cucumber.html"})
public class CucumberRunner {
}