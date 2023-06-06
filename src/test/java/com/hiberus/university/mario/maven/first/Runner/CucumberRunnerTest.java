package com.hiberus.university.mario.maven.first.Runner;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
        @CucumberOptions(
                publish = true,
                plugin = {
                        "pretty",
                        "json:target/surefire-reports/cucumber.json",
                        "html:target/cucumber-html-report.html",
                },
        glue = {
                "com/hiberus/university/mario/maven/first/StepDefs",
                "com/hiberus/university/mario/maven/first/Support"},

        features = {
                "src/test/resources"
        }
)
public class CucumberRunnerTest {

}