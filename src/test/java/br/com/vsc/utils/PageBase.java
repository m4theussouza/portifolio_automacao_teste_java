package br.com.vsc.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.vsc.pages.Constants;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageBase extends Setup {

	private static ExpectedConditions EC;
	private static String spinner = "//mat-spinner[@id='vsc-customer-section-loader']";
	public enum Browser {
		CHROME, IE, FIREFOX;
	}
	
	public enum SelectorType {
		XPATH, ID, CSS, NAME, LINKTEXT, CLASSNAME
	}
	
	public PageBase(){}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public boolean elementExist(SelectorType type, String selector, int secondsWait) throws Exception {
		
		List<WebElement> elements = null;
		int tempo = 0;
		try {
			for (int i = 0; i < secondsWait; i++) {
				switch(type) {
					case XPATH:
						elements = getDriver().findElements( By.xpath(selector) );
						break;
					case ID:
						elements = getDriver().findElements( By.id(selector) );
						break;
					case CSS:
						elements = getDriver().findElements( By.cssSelector(selector) );
						break;
					case NAME:
						elements = getDriver().findElements( By.name(selector) );
						break;
					case LINKTEXT:
						elements = getDriver().findElements( By.linkText(selector) );
						break;
					case CLASSNAME:
						elements = getDriver().findElements( By.className(selector) );
						break;
					default:
						break;
				}
				if( elements.size() > 0 ) {
					System.out.println("Elemento com selector: ["+ selector +"] existe" );
					elements = null;
					return true;
				} else {
					System.out.println("Esperando Elemento com selector: ["+ selector +"] por "+ i +" segundos."  );
					tempo++;
					getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				}
			}
			elements = null;
			throw new Exception("Elemento ["+ selector +"] não foi encontrado em "+ tempo +" segundos");
		} catch (Exception e) {
			elements = null;
			throw new RuntimeException(e);
		}
	}
	
	public WebElement getElementWeb(SelectorType type, String selector) throws InterruptedException {
		
		WebElement element = null;
		try {
			switch(type) {
				case XPATH:
					element = getDriver().findElement( By.xpath(selector) );
					break;
				case ID:
					element = getDriver().findElement( By.id(selector) );
					break;
				case CSS:
					element = getDriver().findElement( By.cssSelector(selector) );
					break;
				case NAME:
					element = getDriver().findElement( By.name(selector) );
					break;
				case LINKTEXT:
					element = getDriver().findElement( By.linkText(selector) );
					break;
				case CLASSNAME:
					element = getDriver().findElement( By.className(selector) );
					break;
				default:
					break;	
			}
		} catch (Exception e) {
			Assert.fail("Elemento [" + selector + "] não encontrado.");
		}
		return element;
	}

	public void clickWebElement(SelectorType type, String selector) throws InterruptedException {
		WebElement element = getElementWeb(type, selector);
		WebDriverWait wait = new WebDriverWait(getDriver(), Constants.TIME_WAIT_ELEMENT_EXIST);
		try {
			switch(type) {
				case XPATH:
					wait.until(EC.elementToBeClickable( By.xpath(selector) ) );
					break;
				case ID:
					wait.until(EC.elementToBeClickable( By.id(selector) ) );
					break;
				case CSS:
					wait.until(EC.elementToBeClickable( By.cssSelector(selector) ) );
					break;
				case NAME:
					wait.until(EC.elementToBeClickable( By.name(selector) ) );
					break;
				case LINKTEXT:
					wait.until(EC.elementToBeClickable( By.linkText(selector) ) );
					break;
				case CLASSNAME:
					wait.until(EC.elementToBeClickable( By.className(selector) ) );
					break;
				default:
					break;
			}
			element.click();
		} catch (Exception e) {
			Assert.fail("Não foi possivel clicar no element [" + selector + "]. " + e);
		}
	}

	public void fillFieldElementWeb(SelectorType type, String selector, String input) throws InterruptedException {
		WebElement element = getElementWeb(type, selector);
		try {
			element.sendKeys(input);
		} catch (Exception e) {
			Assert.fail("Não foi possivel preencher campo do element [" + selector + "]. Erro: [" + e + "]");
		}
	}
	
	public void assertTrueCompararTexto(boolean printScreen, String textPrint, String text1, String text2 ) throws IOException, InterruptedException {
		if( printScreen ) {
			getPrintScreen(textPrint);
		}
		Assert.assertEquals(textPrint, text1, text2);
	}
	
	public void assertTrue(boolean printScreen, String textPrint, boolean condition ) throws IOException, InterruptedException {
		if( printScreen ) {
			getPrintScreen(textPrint);
		}
		Assert.assertTrue(condition);
	}
	
	public void assertFalse(boolean printScreen, String textPrint, boolean condition ) throws IOException, InterruptedException {
		if( printScreen ) {
			getPrintScreen(textPrint);
		}
		Assert.assertFalse(condition);
	}

	public void getPrintScreen(String textPrint) throws IOException, InterruptedException {
		Thread.sleep(1000);
        String pathFolders = getPathsFolders();
        File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        Allure.addAttachment(textPrint, new ByteArrayInputStream(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES)));
        Path projectPath = Paths.get(pathFolders);
        String png = projectPath + "\\" +textPrint+".png";
        FileUtils.copyFile(scrFile, new File(png));
    }

	//Metodo espera espinner se ele existir, se nao existir nao faz nada
	public static void waitSpinner() {
		try {
			WebElement elementSpinner =  new WebDriverWait(getDriver(), Constants.SHORT_TIMEOUT).until(EC.presenceOfElementLocated( By.xpath(spinner) ) );
			System.out.println(elementSpinner.getAttribute("id"));
			//elementSpinner = new WebDriverWait(getDriver(), Constants.LONG_TIMEOUT ).until(EC.not(EC.presenceOfElementLocated( By.xpath(spinner) ) ) );
			System.out.println("elementSpinner.getAttribute()");
		} catch (TimeoutException e) {
			System.out.println("Esperando Spinner");
		}
	}

//	@Attachment(value = "Screenshot", type = "image/png")
//	public byte[] screenshot() {
//		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
//	}

}
