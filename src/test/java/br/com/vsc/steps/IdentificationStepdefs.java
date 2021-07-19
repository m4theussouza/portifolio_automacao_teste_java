package br.com.vsc.steps;

import br.com.vsc.pages.IdentificationPage;
import br.com.vsc.utils.Excel;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.qameta.allure.Step;

public class IdentificationStepdefs {

    IdentificationPage identificationPage = new IdentificationPage();
    Excel vscVendasData = new Excel();

    @Dado("que eu esteja na página de Vendas, preencher dados e avançar")
    public void que_eu_esteja_na_pagina_de_Vendas_preencher_dados_e_avancar() throws Exception {
        identificationPage.validateIdentificationScreen();
        identificationPage.clickButtonCartCPFToYou();
        identificationPage.fillCPFfield( vscVendasData.getData("CPF/CNPJ") );
        identificationPage.clickButtonOkCpfField();
        identificationPage.clickButtonFowardIdentification();
    }
}




