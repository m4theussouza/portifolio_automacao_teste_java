package br.com.mat.test;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.itextpdf.text.DocumentException;

import br.com.mat.steps.LoginSteps;
import br.com.mat.utils.Pdf;
import br.com.mat.utils.Setup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;

public class FazerLoginTest extends Setup {

	public static WebDriver driver;
	String caminhoPastas;
	
	public String caminhoRelativo = ".\\target\\report\\";
	public String nomeScenarioData;
	
	public LoginSteps loginSteps = new LoginSteps();
	
	@Before
	public void setupStart(Scenario scenario) throws IOException {
		DateTimeFormatter dataTempoFormato = DateTimeFormatter.ofPattern("_dd_MM_yyyy_HH_mm_ss");  
		LocalDateTime agora = LocalDateTime.now();
		String dataTexto = dataTempoFormato.format(agora);
		caminhoPastas = scenario.getName();
		caminhoPastas = caminhoPastas.replaceAll("\\s+", "_");
		nomeScenarioData = caminhoPastas + dataTexto;
		caminhoPastas = caminhoRelativo + nomeScenarioData;
		new File(caminhoPastas).mkdir();
		iniciar(driver, caminhoPastas);
	}
	
	@After
	public void setupEnd(Scenario scenario) throws IOException, DocumentException {
		File pastaFonte = new File(caminhoPastas);
		Status status = scenario.getStatus();
		String temp;
		if( status.name().equals("PASSED") ) {
			temp = caminhoPastas;
			caminhoPastas = caminhoPastas + "_" + status;
			new File(caminhoPastas).mkdir();
			copiarArquivos(pastaFonte, caminhoPastas);
			Pdf.generate(caminhoPastas, nomeScenarioData, status);
			new File(temp).delete();
		}
		
		if( status.name().equals("FAILED") ) {
			temp = caminhoPastas;
			caminhoPastas = caminhoPastas + "_" + status;
			new File(caminhoPastas).mkdir();
			tirarPrinteDaTela("PAGINA DE ERRO");
			copiarArquivos(pastaFonte, caminhoPastas);
			Pdf.generate(caminhoPastas, nomeScenarioData, status);
			new File(temp).delete();
		}
		Runtime.getRuntime().exec("mvn clean verify");
	
		fechar();
	}
	
	public static void copiarArquivos(File src, String dstPath) throws IOException {
	    for (File f : src.listFiles()) {
	        String fileName = f.getName();
	        if (fileName.contains(".png")) {
	        	Files.move(f.toPath(), Paths.get(dstPath, fileName), REPLACE_EXISTING);
	        }
	    }
	}
	
	public void tirarPrinteDaTela(String textoPrint) throws IOException {
		String caminhoPastas = getCaminhosPastas();
		File scrFile = ((TakesScreenshot)gerDriver()).getScreenshotAs(OutputType.FILE);
		Path projectPath = Paths.get(caminhoPastas);
		String png = projectPath + "\\" +textoPrint+".png";
		FileUtils.copyFile(scrFile, new File(png));
	}
	
}
