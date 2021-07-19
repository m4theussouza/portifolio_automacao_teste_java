package br.com.vsc.runner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * @author Matheus
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = {"br.com.vsc.steps", "br.com.vsc.test"}, //
		features = "classpath:features",
		tags = {"@CT0001"},
		plugin = { "pretty", "json:target/cucumber.json", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"},
		monochrome = false)
public class CT0001Runner {
}
