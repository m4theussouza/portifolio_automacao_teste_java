package br.com.rsi.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import br.com.rsi.utils.PageBase;

public class LoginPage extends PageBase {
	
	
	String campologinId = "user-name";
	String campoSenhaXpath = "password";
	String botaoLoginXpath = "login-button";
	
	String logoLoginXpath = "//div[@class='login_logo']";
	
	
	public void fazerLoginComSucesso(String username, String senha) throws IOException, InterruptedException {
		if( elementoExiste(TipoSeletor.ID, campologinId, Constantes.TIME_WAIT_ELEMENT_EXIST) ) {
			WebElement campoLogin = pegarElementoWeb(TipoSeletor.ID, campologinId);
			campoLogin.click();
			campoLogin.sendKeys(username);
		}
		
		if( elementoExiste(TipoSeletor.ID, campoSenhaXpath, Constantes.TIME_WAIT_ELEMENT_EXIST) ) {
			WebElement campoSenha = pegarElementoWeb(TipoSeletor.ID, campoSenhaXpath);
			campoSenha.click();
			campoSenha.sendKeys(senha);
		}
		
		if( elementoExiste(TipoSeletor.ID, botaoLoginXpath , Constantes.TIME_WAIT_ELEMENT_EXIST) ) {
			WebElement botaoLogin = pegarElementoWeb(TipoSeletor.ID, botaoLoginXpath);
			botaoLogin.click();
		}
	}
	
	public void validarTelaLogin() throws InterruptedException, IOException {
		assertTrue(Constantes.TIRAR_PRINT, "Validando Tela de Login", elementoExiste(TipoSeletor.XPATH, logoLoginXpath, 
																						Constantes.TIME_WAIT_ELEMENT_EXIST));
	}
	
	public void validarEntradasLogin() throws InterruptedException, IOException {
		assertTrue(Constantes.TIRAR_PRINT, "Validando Entradas Login e Senha", elementoExiste(TipoSeletor.XPATH, logoLoginXpath, 
																						Constantes.TIME_WAIT_ELEMENT_EXIST));
	}
}
