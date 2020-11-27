package br.com.rsi.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.rsi.pages.Constantes;


public class PageBase extends Setup {
	
	public String caminhoRelativo = ".\\target\\report\\";
		
	public enum Navegador {
		CHROME, IE, FIREFOX;
	}
	
	public enum TipoSeletor {
		XPATH, ID, CSS, NAME, LINKTEXT, CLASSNAME
	}
	
	public PageBase(){}
	
	public void setDriver(WebDriver driver_) {
		driver = driver_;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
		
	public void iniciarNavegador(String Url) {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	
	
	
	public boolean elementoExiste(TipoSeletor tipo, String seletor, int segundosEspera) throws InterruptedException {
		
		List<WebElement> elementos = null;
		
		for (int i = 0; i < segundosEspera; i++) {
			switch(tipo) {
				case XPATH:
					elementos = getDriver().findElements( By.xpath(seletor) );
					break;
				case ID:
					elementos = getDriver().findElements( By.id(seletor) );
					break;
				case CSS:
					elementos = getDriver().findElements( By.cssSelector(seletor) );
					break;
				case NAME:
					elementos = getDriver().findElements( By.name(seletor) );
					break;
				case LINKTEXT:
					elementos = getDriver().findElements( By.linkText(seletor) );
					break;
				case CLASSNAME:
					elementos = getDriver().findElements( By.className(seletor) );
					break;
				default:
					break;	
			}
			
			if( elementos.size() > 0 ) {
				System.out.println("Elemento com seletor: ["+ seletor +"] existe" );
				return true;
			} else {
				System.out.println("Esperando Elemento com seletor: ["+ seletor +"] por "+ i +" segundos."  );
				driver.manage().timeouts().implicitlyWait(Constantes.TIME_WAIT_ELEMENT_EXIST, TimeUnit.SECONDS);
			}
			
		}
			return false;
		}
	
	public WebElement pegarElementoWeb(TipoSeletor tipo, String seletor) throws InterruptedException {
		
		WebElement elemento = null;
		try {
			switch(tipo) {
				case XPATH:
					elemento = getDriver().findElement( By.xpath(seletor) );
					break;
				case ID:
					elemento = getDriver().findElement( By.id(seletor) );
					break;
				case CSS:
					elemento = getDriver().findElement( By.cssSelector(seletor) );
					break;
				case NAME:
					elemento = getDriver().findElement( By.name(seletor) );
					break;
				case LINKTEXT:
					elemento = getDriver().findElement( By.linkText(seletor) );
					break;
				case CLASSNAME:
					elemento = getDriver().findElement( By.className(seletor) );
					break;
				default:
					break;	
			}
		} catch (Exception e) {
			Assert.fail("Elemento [" + seletor + "] não encontrado.");
		}
		return elemento;
	}
	
	public void assertTrueCompararTexto(boolean printTela, String textoPrint, String text1, String text2 ) throws IOException {
		if( printTela ) {
			tirarPrinteDaTela(textoPrint);
		}
		Assert.assertEquals(textoPrint, text1, text2);
	}
	
	public void assertTrue(boolean printTela, String textoPrint, boolean condicao ) throws IOException {
		if( printTela ) {
			tirarPrinteDaTela(textoPrint);
		}
		Assert.assertTrue(condicao);
	}
	
	public void assertFalse(boolean printTela, String textoPrint, boolean condicao ) throws IOException {
		if( printTela ) {
			tirarPrinteDaTela(textoPrint);
		}
		Assert.assertFalse(condicao);
	}
	
	public void tirarPrinteDaTela(String textoPrint) throws IOException {
		String caminhoPastas = getCaminhosPastas();
		File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		Path projectPath = Paths.get(caminhoPastas);
		String png = projectPath + "\\" +textoPrint+".png";
		FileUtils.copyFile(scrFile, new File(png));
	}

}
