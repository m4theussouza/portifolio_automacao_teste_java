package br.com.rsi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import br.com.rsi.pages.Constantes;

public class Setup {
		
	public enum Navegador {
		CHROME, IE, FIREFOX;
	}
	
	public static boolean FECHAR_NAVEGADOR = true;
	public static WebDriver driver;
	public static String caminhosPastas;
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



	
	
}
