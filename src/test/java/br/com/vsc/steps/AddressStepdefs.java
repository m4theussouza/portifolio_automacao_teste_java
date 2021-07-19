package br.com.vsc.steps;

import br.com.vsc.pages.AddressPage;
import br.com.vsc.utils.Excel;
import io.cucumber.java.pt.Quando;

public class AddressStepdefs {
    AddressPage addressPage = new AddressPage();
    Excel vscData = new Excel();

    @Quando("estiver na tela de Endereco, preecher dados e avançar")
    public void estiver_na_tela_de_Endereco_preecher_dados_e_avancar() throws Exception {
        addressPage.validateAddressScreen();
        addressPage.fillCEPfield(vscData.getData("CEP"));
        addressPage.fillNumberfield(vscData.getData("Numero"));
        addressPage.clickButtonForwardAddress();
    }
}
