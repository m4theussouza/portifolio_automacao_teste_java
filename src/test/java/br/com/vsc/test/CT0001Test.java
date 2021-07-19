package br.com.vsc.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.itextpdf.text.DocumentException;

import br.com.vsc.utils.Setup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CT0001Test extends Setup {

	public static WebDriver driver;

	@Before("@CT0001")
	public void setupStart(Scenario scenario) throws IOException {
		beforeConfiguracao(scenario);
		iniciar(driver, pathFolders);
	}
	
	@After("@CT0001")
	public void setupEnd(Scenario scenario) throws IOException, DocumentException {
		afterConfiguracao(scenario);
		fechar();
	}
	
}
