package br.com.vsc.utils;

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

import br.com.vsc.pages.Constants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.itextpdf.text.DocumentException;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;

public class Setup {
	public enum Navegador {
		CHROME, IE, FIREFOX;
	}
	
	public static boolean CLOSE_BROWSER = true;
	public static WebDriver driver;
	public static String pathsFolders;
	public static String pathFolders;

	public static String scenarioName;
	public static String relativePath = ".\\target\\report\\";
	public static String nameScenarioDate;
	public static Properties prop = null;
	protected Setup(){}
	
	public static void iniciar(WebDriver driver_init, String paths) throws IOException {
		prop = getProp();
		String url = prop.getProperty("prop.website.url");
		Navegador navegadorOption = Navegador.valueOf(prop.getProperty("prop.website.navegador"));

		driver = driver_init;
		pathsFolders = paths;

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
		driver.manage().timeouts().implicitlyWait(Constants.TIME_WAIT_INIT_BROWSER, TimeUnit.SECONDS);
		driver.get( url );
	}
	
	public static void fechar() {
		
		if (CLOSE_BROWSER){
			tearDown();
		}
		
	}
	
	public static String getPathsFolders() {
		return pathsFolders;
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

	public static String getCTNumber() {
		return scenarioName.substring(0,6);
	}

	private static void setScenarioName(Scenario scenario) {
		scenarioName = scenario.getName();
	}
	
	public static void beforeConfiguracao(Scenario scenario) {
		setScenarioName(scenario);
		DateTimeFormatter dateTempFormat = DateTimeFormatter.ofPattern("_dd_MM_yyyy_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		String dataTexto = dateTempFormat.format(now);
		pathFolders = scenario.getName();
		pathFolders = pathFolders.replaceAll("\\s+", "_");
		pathFolders = pathFolders.replaceAll(",", "");
		nameScenarioDate = pathFolders + dataTexto;
		pathFolders = relativePath + nameScenarioDate;
		new File(pathFolders).mkdir();
		System.out.println(scenario.getSourceTagNames());
	}

	public static void afterConfiguracao(Scenario scenario) throws IOException, com.itextpdf.io.IOException, DocumentException {
		File rootFolder = new File(pathFolders);
		Status status = scenario.getStatus();
		String temp;
		if( status.name().equals("PASSED") ) {
			temp = pathFolders;
			pathFolders = pathFolders + "_" + status;
			new File(pathFolders).mkdir();
			copyFiles(rootFolder, pathFolders);
			Pdf.generate(pathFolders, nameScenarioDate, status);
			new File(temp).delete();
		}
		
		if( status.name().equals("FAILED") ) {
			temp = pathFolders;
			pathFolders = pathFolders + "_" + status;
			new File(pathFolders).mkdir();
			getPrintScreenSetup("PAGINA DE ERRO");
			copyFiles(rootFolder, pathFolders);
			Pdf.generate(pathFolders, nameScenarioDate, status);
			new File(temp).delete();
		}
		//MavenCli cli = new MavenCli();
		//cli.doMain(new String[]{"mvn allure:report"}, "target/allure-results", System.out, System.out);
	}
	
	public static void copyFiles(File src, String dstPath) throws IOException {
	    for (File f : src.listFiles()) {
	        String fileName = f.getName();
	        if (fileName.contains(".png")) {
	        	Files.move(f.toPath(), Paths.get(dstPath, fileName), REPLACE_EXISTING);
	        }
	    }
	}

	public static void getPrintScreenSetup(String textPrint) throws IOException {
		String pathFolders = getPathsFolders();
		File scrFile = ((TakesScreenshot)gerDriver()).getScreenshotAs(OutputType.FILE);
		Path projectPath = Paths.get(pathFolders);
		String png = projectPath + "\\" +textPrint+".png";
		FileUtils.copyFile(scrFile, new File(png));
	}
	
}
	
		
	

