package br.com.mat.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import br.com.mat.utils.PageBase;

public class HomePage extends PageBase {
	
	String elementoParaVerificarErroPaginaId = "about_sidebar_link";
	String divProdutosXpath = "//div[@class='product_label']";
	
	public void validarTelaHome() throws InterruptedException, IOException {
		assertTrue(Constantes.TIRAR_PRINT, "Validando Home Page", elementoExiste(TipoSeletor.XPATH, divProdutosXpath,
																				Constantes.TIME_WAIT_ELEMENT_EXIST));
	}

	public void validarUsuarioComProblema() throws IOException, InterruptedException {
		if( elementoExiste(TipoSeletor.ID, elementoParaVerificarErroPaginaId, Constantes.TIME_WAIT_ELEMENT_EXIST) ) {
			WebElement erroPagina = pegarElementoWeb(TipoSeletor.ID, elementoParaVerificarErroPaginaId);
			assertTrue(Constantes.TIRAR_PRINT, "Validando Login Usuario Com Erro", erroPagina.getAttribute("href").contains("error"));
		}
		
	}

	public void validarUsuarioPerformance() throws InterruptedException, IOException {
		validarTelaHome();
	}
}
