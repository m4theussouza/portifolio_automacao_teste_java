package br.com.mat.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.yaml.snakeyaml.scanner.Constant;

import br.com.mat.utils.PageBase;

public class LoginPage extends PageBase {
	
	
	String h3ErrorLoginXpath = "//h3[text()='Sorry, this user has been locked out.']";
	
	String campologinId = "user-name";
	String campoSenhaId = "password";
	
	String botaoLoginId = "login-button";
	
	String logoLoginXpath = "//div[@class='login_logo']";
	
	
	public void fazerLogin(String username, String senha) throws IOException, InterruptedException {
		if( elementoExiste(TipoSeletor.ID, campologinId, Constantes.TIME_WAIT_ELEMENT_EXIST) ) {
			WebElement campoLogin = pegarElementoWeb(TipoSeletor.ID, campologinId);
			campoLogin.click();
			campoLogin.sendKeys(username);
		}
		
		if( elementoExiste(TipoSeletor.ID, campoSenhaId, Constantes.TIME_WAIT_ELEMENT_EXIST) ) {
			WebElement campoSenha = pegarElementoWeb(TipoSeletor.ID, campoSenhaId);
			campoSenha.click();
			campoSenha.sendKeys(senha);
		}
		
	}
	
	public void clickButtonLogin() throws InterruptedException {
		if( elementoExiste(TipoSeletor.ID, botaoLoginId, Constantes.TIME_WAIT_ELEMENT_EXIST) ) {
			WebElement botaoLogin = pegarElementoWeb(TipoSeletor.ID, botaoLoginId);
			botaoLogin.click();
		}
	}
	
	public void validarTelaLogin() throws InterruptedException, IOException {
		assertTrue(Constantes.TIRAR_PRINT, "Validando Tela de Login", elementoExiste(TipoSeletor.XPATH, logoLoginXpath, 
																						Constantes.TIME_WAIT_ELEMENT_EXIST));
	}
	
	public void validarEntradasLogin() throws InterruptedException, IOException {
		assertTrue(Constantes.TIRAR_PRINT, "Validando Entradas Login e Senha", elementoExiste(TipoSeletor.ID, campoSenhaId, 
																				Constantes.TIME_WAIT_ELEMENT_EXIST));
	}

	public void validarUsuarioBloqueado() throws IOException, InterruptedException {
		assertTrue(Constantes.TIRAR_PRINT, "Validando Entradas Login e Senha", elementoExiste(TipoSeletor.XPATH, h3ErrorLoginXpath, 
																			Constantes.TIME_WAIT_ELEMENT_EXIST)); 
	}
}
