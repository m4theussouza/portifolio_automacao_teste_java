package br.com.mat.steps;

import java.io.IOException;

import br.com.mat.pages.LoginPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class LoginSteps {
	
	LoginPage loginPage = new LoginPage();
	
	@Dado("que eu esteja na página do SWAGLABS")
	public void que_eu_esteja_na_página_do_SWAGLABS() throws IOException, InterruptedException {
		loginPage.validarTelaLogin();
	}

	@Quando("eu fizer login com o {string} e {string}")
	public void eu_fizer_login_com_o(String usuario, String senha) throws IOException, InterruptedException {
		loginPage.fazerLoginComSucesso(usuario, senha);
		loginPage.validarEntradasLogin();
	}

	@Então("valido que o login teve status {string}")
	public void valido_que_o_login_foi_realizado(String status) {
		System.out.println("valido que o login foi realizado "+status);
	    // Write code here that turns the phrase above into concrete actions
	}

}




