package br.com.rsi.steps;


import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class BuscaGoogleSteps {

	@Dado("que eu esteja na página do Google")
	public void queEuEstejaNaPaginaDoGoogle() {
		
	}

	@Quando("eu buscar pela palavra {string}")
	public void euBuscarPelaPalavra(String palavra) {
	}

	@Então("eu valido que a busca foi realizada")
	public void euValidoQueABuscaFoiRealizada() {
	}

}
