package br.com.rsi.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

/**
 * @author Matheus
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = {"br.com.rsi.steps","br.com.rsi.test", "br.com.rsi.utils"}, //
		features = "classpath:features", //
		plugin = { "pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json" },
		tags = "@Login", //
		monochrome = true)
public class Runner extends AbstractTestNGCucumberTests {

}
