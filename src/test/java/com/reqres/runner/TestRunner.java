package com.reqres.runner;

import io.cucumber.junit.*;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/reqres/features",   // Path to feature files
        glue = "com.reqres.steps",             // Path to step definition classes
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",  // Change the HTML report path
                "json:target/cucumber-reports/cucumber.json",   // Change the JSON report path
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }   // Plugins for reporting
)

public class TestRunner {
}