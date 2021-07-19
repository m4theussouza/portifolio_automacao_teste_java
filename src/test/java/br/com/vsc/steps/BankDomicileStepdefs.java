package br.com.vsc.steps;

import br.com.vsc.pages.BankDomicilePage;
import br.com.vsc.utils.Excel;
import io.cucumber.java.pt.Quando;

public class BankDomicileStepdefs {
    Excel vscData = new Excel();
    BankDomicilePage bankDomicilePage = new BankDomicilePage();
    @Quando("estiver na tela de Domicilio Bancario, preecher dados e avançar")
    public void estiver_na_tela_de_Domicilio_Bancario() throws Exception {
        bankDomicilePage.validateBankDomicileScreen();
        bankDomicilePage.selectBank(vscData.getData("Banco"));
        bankDomicilePage.selectCheckingAccount();
        bankDomicilePage.fillAgency(vscData.getData("Agencia"));
        bankDomicilePage.fillAccountAndDigit(vscData.getData("Conta"), vscData.getData("Digito"));
        bankDomicilePage.clickButtonForwardBankDomicile();
    }
}
