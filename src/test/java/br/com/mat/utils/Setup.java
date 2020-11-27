package br.com.mat.utils;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.itextpdf.text.DocumentException;

import br.com.mat.pages.Constantes;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;

public class Setup {
	public enum Navegador {
		CHROME, IE, FIREFOX;
	}
	
	public static boolean FECHAR_NAVEGADOR = true;
	public static WebDriver driver;
	public static String caminhosPastas;
	public static String caminhoPastas;
	
	public static String caminhoRelativo = ".\\target\\report\\";
	public static String nomeScenarioData;
	protected Setup(){}
	
	public static void iniciar(WebDriver driver_init, String caminhos) throws IOException {
		Properties prop = getProp();
		String url = prop.getProperty("prop.website.url");
		Navegador navegadorOption = Navegador.valueOf(prop.getProperty("prop.website.navegador"));
		
		
		driver = driver_init;
		caminhosPastas = caminhos;

		switch ( navegadorOption ) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation")); 
				options.addArguments("--disable-infobars");
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);					
			break;
		
			case IE:
				System.setProperty("webdriver.ie.driver", "src\\test\\resources\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();				
			break;
				
			case FIREFOX:
				System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
				driver = new FirefoxDriver();	
			break;			
			
			default:			
				System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
				driver = new ChromeDriver();
			break;
		}
	
		driver.manage().window().maximize();			
		driver.manage().timeouts().implicitlyWait(Constantes.TIME_WAIT_INIT_BROWSER, TimeUnit.SECONDS);
		driver.get( url );
	}
	
	public static void fechar() {
		
		if (FECHAR_NAVEGADOR){
			tearDown();
		}
		
	}
	
	public static String getCaminhosPastas() {
		return caminhosPastas;
	}
	
	public static WebDriver gerDriver() {
		return driver;
	}
	
	public static void tearDown() {
		if (driver != null){
			driver.close();
			driver.quit();
			driver = null;
		}
	}
	
	
	public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("src"+File.separator+
    												"test"+File.separator+
    												"resources"+File.separator+
    												"start.properties");
        props.load(file);
        return props;
	}
	
	public static void beforeConfiguracao(Scenario scenario) {
		
		DateTimeFormatter dataTempoFormato = DateTimeFormatter.ofPattern("_dd_MM_yyyy_HH_mm_ss");  
		LocalDateTime agora = LocalDateTime.now();
		String dataTexto = dataTempoFormato.format(agora);
		caminhoPastas = scenario.getName();
		caminhoPastas = caminhoPastas.replaceAll("\\s+", "_");
		nomeScenarioData = caminhoPastas + dataTexto;
		caminhoPastas = caminhoRelativo + nomeScenarioData;
		new File(caminhoPastas).mkdir();
		System.out.println(scenario.getSourceTagNames());
	}
	
	public static void afterConfiguracao(Scenario scenario) throws IOException, com.itextpdf.io.IOException, DocumentException {

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
			tirarPrinteDaTelaSetup("PAGINA DE ERRO");
			copiarArquivos(pastaFonte, caminhoPastas);
			Pdf.generate(caminhoPastas, nomeScenarioData, status);
			new File(temp).delete();
		}
	}
	
	public static void copiarArquivos(File src, String dstPath) throws IOException {
	    for (File f : src.listFiles()) {
	        String fileName = f.getName();
	        if (fileName.contains(".png")) {
	        	Files.move(f.toPath(), Paths.get(dstPath, fileName), REPLACE_EXISTING);
	        }
	    }
	}
	
	public static void tirarPrinteDaTelaSetup(String textoPrint) throws IOException {
		String caminhoPastas = getCaminhosPastas();
		File scrFile = ((TakesScreenshot)gerDriver()).getScreenshotAs(OutputType.FILE);
		Path projectPath = Paths.get(caminhoPastas);
		String png = projectPath + "\\" +textoPrint+".png";
		FileUtils.copyFile(scrFile, new File(png));
	}
	
}
	
		
	

